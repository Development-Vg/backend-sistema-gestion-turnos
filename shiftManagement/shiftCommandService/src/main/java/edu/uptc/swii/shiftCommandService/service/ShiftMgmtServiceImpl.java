package edu.uptc.swii.shiftCommandService.service;

import edu.uptc.swii.shiftCommandService.domain.model.Shift;
import edu.uptc.swii.shiftCommandService.domain.repository.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Service
public class ShiftMgmtServiceImpl implements ShiftMgmtService{
    @Autowired
    ShiftRepository shiftRepository;

    @Override
    public Shift saveShift(Shift shift) {
       ZonedDateTime actual =  shift.getDate().withHour(0).withMinute(0).withSecond(0);
        //for(Shift shift : shiftRepository.findByDate(actual, actual.plusHours(23).plusMinutes(59).plusSeconds(59))){
        if(shiftRepository.findByDateRange(actual, actual.plusHours(23).plusMinutes(59).plusSeconds(59)).isEmpty()) {
            shiftRepository.save(shift);
            return shift;
        }
        return null;
    }

    @Override
    public void deleteAll() {
        shiftRepository.deleteAll();
    }

    @Override
    public boolean updateStatusTurn(int userId, int idTurn) {
        Shift shift = shiftRepository.findByUserIdAndId(userId, idTurn);
        if (shift != null){
            shift.changeStatus();
            shiftRepository.save(shift);
            return true;
        }
        return false;
    }

    public ZonedDateTime convertDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd yyyy HH:mm:ss 'GMT'Z", new Locale("en", "CO") );
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(date, formatter);
        return zonedDateTime;
    }
}