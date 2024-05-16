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
        int id = autoIncrement();
        shift.setId(id);
        shift.setShiftNumber("M"+ (id+1));
        shiftRepository.save(shift);
    }

    @Override
    public List<Shift> listAllUser() {
        return shiftRepository.findAll();
    }
    private int autoIncrement() {
        List<Shift> products = shiftRepository.findAll();
        return products.isEmpty()? 1 :
                products.stream().max(Comparator.comparing(Shift::getId)).get().getId() + 1;
    }
}
