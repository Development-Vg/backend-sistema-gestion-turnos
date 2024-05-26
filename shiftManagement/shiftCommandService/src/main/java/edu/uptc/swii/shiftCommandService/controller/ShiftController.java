package edu.uptc.swii.shiftCommandService.controller;

import edu.uptc.swii.shiftCommandService.domain.model.Shift;
import edu.uptc.swii.shiftCommandService.service.ShiftMgmtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;


@RestController
@RequestMapping("/turnos")
public class ShiftController {
    @Value("")
    private String kafkaessage;
    @Autowired
    private ShiftMgmtService shiftMgmtService;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC = "turnos";


    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json")
    public String createUser(@RequestBody Shift shift) {
        while(kafkaessage.equals("")) {
            kafkaTemplate.send(TOPIC, "idTurno");
        }
        shift.setId(Integer.parseInt(kafkaessage));
        shiftMgmtService.saveShift(shift);
        kafkaessage = "";
        return "Turnoid: " + shift.getId();
    }

    @RequestMapping(value = "/updateStatus/{userId}", method = RequestMethod.PATCH, produces = "application/json")
    public ResponseEntity updateStatusTurn(@PathVariable int userId){
        return shiftMgmtService.updateStatusTurn(userId) ? new ResponseEntity<>("Se actualizo correctamente", HttpStatus.OK) : new ResponseEntity<>("No se actualizo correctamente", HttpStatus.NOT_FOUND) ;
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity eliminarTodos() {
        shiftMgmtService.deleteAll();
        return new ResponseEntity<>("se elimino", HttpStatus.OK);
    }

    @KafkaListener(topics = "turnos-1", groupId = "myGroup")
    public void listen(String message) {
        kafkaessage = message;
    }

}
