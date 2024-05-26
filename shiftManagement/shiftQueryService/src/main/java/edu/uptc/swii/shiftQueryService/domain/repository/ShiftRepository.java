package edu.uptc.swii.shiftQueryService.domain.repository;

import edu.uptc.swii.shiftQueryService.domain.model.Shift;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface ShiftRepository extends MongoRepository<Shift, Integer> {
    //List<Shift> findByDependence(String dependence);
    @Query("{ 'dependence': ?0, 'date' : { $gte: ?1, $lt: ?2 } }")
    List<Shift> findByDependenceAndDate(String dependence, ZonedDateTime startOfDay, ZonedDateTime endOfDay);

    @Query("{ 'date' : { $gte: ?0, $lt: ?1 } }")
    List<Shift> findByDate(ZonedDateTime startOfDay, ZonedDateTime endOfDay);

    List<Shift> findByUserId (int userId);
}
