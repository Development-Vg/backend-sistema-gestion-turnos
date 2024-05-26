package edu.uptc.swii.shiftQueryService.service;

import edu.uptc.swii.shiftQueryService.domain.model.Shift;

import java.util.List;

public interface ShiftMgmtService {
    public List<Shift> listAllShift();
    public List<String> listTurnsAsing(int Userid,String dependence, String date);
}
