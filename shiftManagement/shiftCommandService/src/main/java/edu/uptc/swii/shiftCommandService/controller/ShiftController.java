package edu.uptc.swii.shiftCommandService.controller;

import edu.uptc.swii.shiftCommandService.domain.model.Shift;
import edu.uptc.swii.shiftCommandService.service.ShiftMgmtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/turnos")
public class ShiftController {
    @Autowired
    private ShiftMgmtService shiftMgmtService;

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json")
    public String createUser(@RequestBody Shift shift) {

        shiftMgmtService.saveShift(shift);
        return "Turnoid: " + shift.getId();
    }

    @RequestMapping(value = "/listAll", method = RequestMethod.GET, produces = "application/json")
    public List<Shift> listUsers(){
        return shiftMgmtService.listAllUser();
    }
}
