package edu.uptc.swii.shiftCommandService.service;

import edu.uptc.swii.shiftCommandService.domain.model.Shift;
import edu.uptc.swii.shiftCommandService.domain.repository.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class ShiftMgmtServiceImpl implements ShiftMgmtService{
    @Autowired
    ShiftRepository shiftRepository;
    @Override
    public void saveShift(Shift shift) {
        shiftRepository.save(shift);
    }

    @Override
    public void deleteAll() {
        shiftRepository.deleteAll();
    }
}