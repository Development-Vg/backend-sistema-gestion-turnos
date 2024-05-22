package edu.uptc.swii.notifications.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ScheduledTasks {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPICSHIFT = "notificacion";
    private static final String TOPICSUSERS= "Usernot";

    @Value("")
    public String shifts;

    @Value("")
    List<String> listShifh;

    @Scheduled(cron = "*/10 * 0-23 * * ?")// La expresión cron para ejecutar una tarea cada 10 segundos, desde las 8:00 AM hasta las 11:00 PM, todos los días,
    public void checkMavenVersion() {
        System.out.println("hola");
        kafkaTemplate.send(TOPICSHIFT, "idTurno");
        List<String> shiftList = Arrays.asList(shifts.split("\n"));
        shiftList.forEach(s -> {
            System.out.println(kafkaTemplate.send(TOPICSHIFT, ));
        });

    }
//        LocalTime now = LocalTime.now();
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

    @KafkaListener(topics = "notificaciones1", groupId = "myGroup")
    public void ListShif(String message){
        shifts = message;
    }
}

