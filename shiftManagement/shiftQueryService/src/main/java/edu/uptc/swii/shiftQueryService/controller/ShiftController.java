package edu.uptc.swii.shiftQueryService.controller;

import edu.uptc.swii.shiftQueryService.domain.model.Shift;
import edu.uptc.swii.shiftQueryService.service.ShiftMgmtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/turnosList")
public class ShiftController {
    @Autowired
    private ShiftMgmtService shiftMgmtService;

    @RequestMapping(value = "/listAll", method = RequestMethod.GET, produces = "application/json")
    public List<Shift> listUsers(){
        System.out.println(LocalDateTime.now());
        return shiftMgmtService.listAllShift();
    }

    @RequestMapping(value = "/listAvailableShifts", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity listAw (@RequestParam int userId, @RequestParam String dependence, @RequestParam String date){
            List<String>  filterShiftDependence = shiftMgmtService.listTurnsAsing(userId, dependence, date);
        System.out.println(date + "     fecha llega");
        return !(filterShiftDependence == null)? new ResponseEntity<>(filterShiftDependence, HttpStatus.OK) : new ResponseEntity<>("Usted ya tiene un turno registrado hoy en esa dependencia", HttpStatus.NOT_FOUND);
    }
}
