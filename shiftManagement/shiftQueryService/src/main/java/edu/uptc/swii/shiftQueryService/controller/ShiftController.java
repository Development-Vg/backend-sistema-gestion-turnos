package edu.uptc.swii.shiftQueryService.controller;

import edu.uptc.swii.shiftQueryService.domain.model.Shift;
import edu.uptc.swii.shiftQueryService.service.ShiftMgmtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/turnosList")
public class ShiftController {
    @Autowired
    private ShiftMgmtService shiftMgmtService;

    @RequestMapping(value = "/listAll", method = RequestMethod.GET, produces = "application/json")
    public List<Shift> listUsers(){
        return shiftMgmtService.listAllShift();
    }

    @RequestMapping(value = "/listAvailableShifts", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity listAw (@RequestParam String dependence, @RequestParam String date){
            List<String>  filterShiftDependence = shiftMgmtService.list(dependence, date);
        return !(filterShiftDependence == null)? new ResponseEntity<>(filterShiftDependence, HttpStatus.OK) : new ResponseEntity<>("No turnos disponible", HttpStatus.NOT_FOUND);
    }
}
