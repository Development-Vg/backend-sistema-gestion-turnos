package edu.uptc.swii.shiftQueryService.controller;

import edu.uptc.swii.shiftQueryService.domain.model.Shift;
import edu.uptc.swii.shiftQueryService.service.ShiftMgmtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity listAw (@RequestBody Map<String, Object> requestData){
            List<String>  filterShiftDependence = shiftMgmtService.list((String) requestData.get("dependence"), (String) requestData.get("date"));
        return !(filterShiftDependence == null)? new ResponseEntity<>(filterShiftDependence, HttpStatus.OK) : new ResponseEntity<>("No turnos disponible", HttpStatus.NOT_FOUND);
    }
}
