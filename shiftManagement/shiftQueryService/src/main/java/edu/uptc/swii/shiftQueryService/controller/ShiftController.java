package edu.uptc.swii.shiftQueryService.controller;

import edu.uptc.swii.shiftQueryService.domain.model.Shift;
import edu.uptc.swii.shiftQueryService.service.ShiftMgmtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/turnosList")
public class ShiftController {
    @Autowired
    private ShiftMgmtService shiftMgmtService;

    @RequestMapping(value = "/listAll", method = RequestMethod.GET, produces = "application/json")
    public List<Shift> listUsers(){
        return shiftMgmtService.listAllUser();
    }
}
