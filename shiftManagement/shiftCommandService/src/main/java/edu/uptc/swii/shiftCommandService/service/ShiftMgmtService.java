package edu.uptc.swii.shiftCommandService.service;

import edu.uptc.swii.shiftCommandService.domain.model.Shift;

import java.util.List;

public interface ShiftMgmtService {
    public void saveShift(Shift shift);
    public List<Shift> listAllUser();
}
