package edu.uptc.swii.notifications.component;

import edu.uptc.swii.notifications.service.IEmailService;
import edu.uptc.swii.notifications.util.Email;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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

    @Autowired
    private IEmailService emailService;

    private static final String TOPICSHIFT = "notificacion";
    private static final String TOPICSUSERS = "Usernot";

    @Value("")
    public String shifts;

    @Value("")
    String user;

    @Value("")
    List<String> listShifh;

    @Scheduled(cron = "0 * 0-23 * * ?")// La expresión cron para ejecutar una tarea cada 10 segundos, desde las 8:00 AM hasta las 11:00 PM, todos los días,
    public void checkMavenVersion() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd yyyy HH:mm:ss 'GMT'Z", new Locale("en", "CO"));
        DateTimeFormatter formatterTwo = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        kafkaTemplate.send(TOPICSHIFT, "idTurno");
        if (!shifts.isEmpty()) {
            List<String> shiftList = Arrays.asList(shifts.split("\n"));
            shiftList.forEach(s -> {
                kafkaTemplate.send(TOPICSUSERS, s.split("--")[0]);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
                ZonedDateTime zonedDateTim = ZonedDateTime.now();
                zonedDateTim.format(formatterTwo);
                ZonedDateTime zonedDateTime = ZonedDateTime.parse(s.split("--")[1], formatter);
                zonedDateTime = zonedDateTime.minusMinutes(15);
                zonedDateTime.format(formatterTwo);
                LocalDateTime truncatedDate1 = zonedDateTime.toLocalDateTime().truncatedTo(ChronoUnit.MINUTES);
                LocalDateTime truncatedDate2 = zonedDateTim.toLocalDateTime().truncatedTo(ChronoUnit.MINUTES);
                s.split(s.split("--")[0]);
                if(truncatedDate1.isEqual(truncatedDate2)) {
                    try {
                        emailService.sendEmail(user.split("\n")[0], Email.emailSubject(zonedDateTime), Email.bodyEmail(zonedDateTime, user, s.split("--")[2]));
                    } catch (MessagingException e) {
                        throw new RuntimeException(e);
                    }
                }
               user = "";
            });
        }
    }

    @KafkaListener(topics = "Usernot1", groupId = "myGroup")
    public void getUser(String message){
        user = message;
    }

    @KafkaListener(topics = "notificaciones1", groupId = "myGroup")
    public void ListShif(String message){
        shifts = message;
    }
}

