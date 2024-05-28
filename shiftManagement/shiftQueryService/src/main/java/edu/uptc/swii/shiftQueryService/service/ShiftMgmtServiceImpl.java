package edu.uptc.swii.shiftQueryService.service;

import edu.uptc.swii.shiftQueryService.domain.model.Shift;
import edu.uptc.swii.shiftQueryService.domain.repository.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import java.util.List;

@Service
public class ShiftMgmtServiceImpl implements ShiftMgmtService {
    @Autowired
    ShiftRepository shiftRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "turnos-1";
    private static final String TOPICTWO = "notificaciones1";

    @Override
    public List<Shift> listAllShift() {
        changeStatusInactive();
        return shiftRepository.findAll();
    }

    @Override
    public List<Shift> ShiftByIdUser(int userId) {
        changeStatusInactive();
        return shiftRepository.findByUserId(userId);
    }

    public void changeStatusInactive(){
        List<Shift> shifts =   shiftRepository.findActiveShiftsBeforeNow(ZonedDateTime.now());
        if(!shifts.isEmpty()) {
            shifts.forEach(shift -> {
                shift.changeStatus();
                shiftRepository.save(shift);
            });
        }
    }

    @KafkaListener(topics = "turnos", groupId = "myGroup")
    private void autoIncrement(String message) {
        if (message.equals("idTurno")) {
            List<Shift> products = shiftRepository.findAll();
            int number = products.isEmpty() ? 1 :
                    products.stream().max(Comparator.comparing(Shift::getId)).get().getId() + 1;
            kafkaTemplate.send(TOPIC, String.valueOf(number));
            //generateDateTimeList().forEach(System.out::println);
        }
    }

    @KafkaListener(topics = "notificacion", groupId = "myGroup")
    public void listShiftNotificaions(String message){
        String listShift = "";
        ZonedDateTime actual = ZonedDateTime.now();
        actual =  actual.withHour(0).withMinute(0).withSecond(0);
        for(Shift shift : shiftRepository.findByDate(actual, actual.plusHours(23).plusMinutes(59).plusSeconds(59))){
            ZonedDateTime dateTurn = shift.getDate();
            listShift += shift.getUserId() + "--" + dateTurn.format(DateTimeFormatter.ofPattern("EEE MMM dd yyyy HH:mm:ss 'GMT'Z", new Locale("en", "CO"))) + "--" +shift.getDependence();
            listShift += "\n";
        }
        kafkaTemplate.send(TOPICTWO, listShift);
    }

    public ZonedDateTime convertDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd yyyy HH:mm:ss 'GMT'Z", new Locale("en", "CO") );
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(date, formatter);
        return zonedDateTime;
    }

    @Override
    public List<String> listTurnsAsing(int userId, String dependence, String date) {
        List<Shift> filterShiftDependence = shiftRepository.findByDependenceAndDateAndStatus(dependence, convertDate(date), convertDate(date).plusHours(23).plusMinutes(59).plusSeconds(59));
        if(filterShiftDependence.isEmpty()) {
            return generateDateTimeList(date);
        }
        return availableShifts(date, filterShiftDependence);
    }

    public List<String> generateDateTimeList(String dateOne) {
        LocalTime startTime = LocalTime.of(8, 0);
        LocalTime endTime = LocalTime.of(17, 0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd yyyy HH:mm:ss 'GMT'Z", new Locale("en", "CO") );
        ZonedDateTime zonedDateTim = ZonedDateTime.parse(dateOne, formatter);
        LocalDate date = zonedDateTim.toLocalDate();
        List<String> dateTimeList = new ArrayList<>();
        ZoneId colombiaZoneId = ZoneId.of("America/Bogota");
        LocalDateTime currentDateTime = LocalDateTime.of(date, startTime);
        while (!currentDateTime.toLocalTime().isAfter(endTime)) {
            ZonedDateTime zonedDateTime = ZonedDateTime.of(currentDateTime, colombiaZoneId);
            dateTimeList.add(zonedDateTime.format(formatter));
            currentDateTime = currentDateTime.plusMinutes(30);
        }
        return dateTimeList;
    }

    public List<String> availableShifts(String date, List<Shift> filterShiftDependence){
        List<Shift> shiftList = filterShiftDependence; // Supongamos que ya tienes implementado un método para obtener la lista de Shifts
        List<String> dateTimeList = generateDateTimeList(date); // Supongamos que ya tienes implementado un método para obtener la lista de fechas
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd yyyy HH:mm:ss 'GMT'Z", new Locale("en", "CO") );
        ZoneId colombiaZoneId = ZoneId.of("America/Bogota");
        for (Shift shift : shiftList) {
            ZonedDateTime shiftDateTime = shift.getDate();
            Iterator<String> iterator = dateTimeList.iterator();
            while (iterator.hasNext()) {
                String dateTimeString = iterator.next();
                ZonedDateTime dateTime = ZonedDateTime.parse(dateTimeString, formatter);
                if (dateTime.isEqual(shiftDateTime)) {
                    iterator.remove();
                }
            }
        }
        return dateTimeList;
    }
}
