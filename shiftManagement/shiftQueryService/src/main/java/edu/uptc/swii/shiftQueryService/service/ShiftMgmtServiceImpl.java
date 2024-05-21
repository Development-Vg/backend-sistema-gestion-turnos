package edu.uptc.swii.shiftQueryService.service;

import edu.uptc.swii.shiftQueryService.domain.model.Shift;
import edu.uptc.swii.shiftQueryService.domain.repository.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class ShiftMgmtServiceImpl implements ShiftMgmtService{
    @Autowired
    ShiftRepository shiftRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "turnos-1";

    @Override
    public List<Shift> listAllUser() {
        return shiftRepository.findAll();
    }

    @KafkaListener(topics = "turnos", groupId = "myGroup")
    private void autoIncrement(String message) {
        List<Shift> products = shiftRepository.findAll();
        int number = products.isEmpty()? 1 :
                products.stream().max(Comparator.comparing(Shift::getId)).get().getId() + 1;
        kafkaTemplate.send(TOPIC, String.valueOf(number));
    }
}
