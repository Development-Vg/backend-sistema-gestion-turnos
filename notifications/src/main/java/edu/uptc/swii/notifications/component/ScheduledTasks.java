package edu.uptc.swii.notifications.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Component
public class ScheduledTasks {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPICSHIFT = "notificacion";
    private static final String TOPICSUSERS= "Usernot";

    @Value("")
    public String shifts;

    @Value("0")
    public int count;

    @Value("")
    String user;

    @Value("")
    List<String> listShifh;

    @Scheduled(cron = "*/10 * 0-23 * * ?")// La expresión cron para ejecutar una tarea cada 10 segundos, desde las 8:00 AM hasta las 11:00 PM, todos los días,
    public void checkMavenVersion() {
        //String fecha = "Thu May 23 2024 00:32:00 GMT-0500";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd yyyy HH:mm:ss 'GMT'Z", new Locale("en", "CO"));
        DateTimeFormatter formatterTwo = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

//        System.out.println(zonedDateTim);
//        System.out.println("hola");
        kafkaTemplate.send(TOPICSHIFT, "idTurno");
        if (!shifts.isEmpty()) {
            List<String> shiftList = Arrays.asList(shifts.split("\n"));
            shiftList.forEach(s -> {
                kafkaTemplate.send(TOPICSUSERS, s.split("--")[0]);
                System.out.println(s.split("--")[0]);
                ZonedDateTime zonedDateTim = ZonedDateTime.now();

                //ZonedDateTime truncatedDate2 = date2.truncatedTo(java.time.temporal.ChronoUnit.MINUTES);

                //zonedDateTim.format(formatter);
                zonedDateTim.format(formatterTwo);
                ZonedDateTime zonedDateTime = ZonedDateTime.parse(s.split("--")[1], formatter);
               // ZonedDateTime zonedDateTime = ZonedDateTime.parse(fecha, formatter);

                zonedDateTime = zonedDateTime.minusMinutes(15);
                zonedDateTime.format(formatterTwo);

                LocalDateTime truncatedDate1 = zonedDateTime.toLocalDateTime().truncatedTo(ChronoUnit.MINUTES);
                LocalDateTime truncatedDate2 = zonedDateTim.toLocalDateTime().truncatedTo(ChronoUnit.MINUTES);

                System.out.println(truncatedDate1 + "                 " + truncatedDate2);
                System.out.println(user);
                System.out.println(zonedDateTime.isEqual(zonedDateTim) + "        validacion");
                if(truncatedDate1.isEqual(truncatedDate2) & count==0){
                    System.out.println("Se envion correo");
                    count ++;
                }else if(!truncatedDate1.isEqual(truncatedDate2)){
                    count=0;
                }
                try {
                    // Esperar 1 segundo (1000 milisegundos)
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
            });
            System.out.println();
            System.out.println();
            System.out.println();
        }
    }
//
//        LocalTime startTime = LocalTime.of(8, 0);
//        LocalTime endTime = LocalTime.of(9, 0);
//
//        if (!now.isBefore(startTime) && !now.isAfter(endTime)) {
//            try {
//                // Ejecutar el comando 'mvn -v'
//                Process process = Runtime.getRuntime().exec("mvn -v");
//                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//                String line;
//
//                // Leer y registrar la salida del comando
//                while ((line = reader.readLine()) != null) {
//                    System.out.println(line);
//                }
//
//                process.waitFor();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
    @KafkaListener(topics = "Usernot1", groupId = "myGroup")
    public void getUser(String message){
        user = message;
    }

    @KafkaListener(topics = "notificaciones1", groupId = "myGroup")
    public void ListShif(String message){
        shifts = message;
    }
}

