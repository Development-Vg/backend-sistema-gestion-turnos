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

import java.util.List;


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

    @KafkaListener(topics = "turnos-1", groupId = "myGroup")
    public void listen(String message) {
        kafkaessage = message;
    }
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json")
    public String createUser(@RequestBody Shift shift) {
        while(kafkaessage.equals("")) {
            kafkaTemplate.send(TOPIC, "idTurno");

        }
        shift.setId(Integer.parseInt(kafkaessage));
        shift.setShiftNumber("M" + (shift.getId() +1));
        shiftMgmtService.saveShift(shift);
        return "Turnoid: " + shift.getId();
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity eliminarTodos() {
        shiftMgmtService.deleteAll();
        return new ResponseEntity<>("se elimino", HttpStatus.OK);
    }
}
