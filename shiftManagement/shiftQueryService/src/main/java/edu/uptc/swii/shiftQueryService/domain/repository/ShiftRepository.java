package edu.uptc.swii.shiftQueryService.domain.repository;

import edu.uptc.swii.shiftQueryService.domain.model.Shift;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShiftRepository extends MongoRepository<Shift, Integer> {
    List<Shift> findByDependence(String dependence);
}
