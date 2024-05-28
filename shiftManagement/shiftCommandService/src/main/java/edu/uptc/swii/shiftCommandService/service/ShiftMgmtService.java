package edu.uptc.swii.shiftCommandService.service;

import edu.uptc.swii.shiftCommandService.domain.model.Shift;

import java.util.List;

public interface ShiftMgmtService {
    public Shift saveShift(Shift shift);
    public void deleteAll();
    public boolean updateStatusTurn(int userId, int idTurn);
}
