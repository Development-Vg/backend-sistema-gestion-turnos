package edu.uptc.swii.notifications.component;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    @Scheduled(cron = "*/10 * 8-22 * * ?")// La expresión cron para ejecutar una tarea cada 10 segundos, desde las 8:00 AM hasta las 11:00 PM, todos los días,
    public void checkMavenVersion() {
        System.out.println("hola");

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
}

