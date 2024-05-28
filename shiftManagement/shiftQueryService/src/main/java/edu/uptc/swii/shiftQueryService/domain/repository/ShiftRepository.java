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
    @Query("{ 'dependence': ?0, 'date' : { $gte: ?1, $lt: ?2 }, 'status': { $in: ['ACTIVE', 'INACTIVE'] } }")
    List<Shift> findByDependenceAndDateAndStatus(String dependence, ZonedDateTime startOfDay, ZonedDateTime endOfDay);

    @Query("{ 'date' : { $gte: ?0, $lt: ?1 }, 'status': 'ACTIVE' }")
    List<Shift> findByDate(ZonedDateTime startOfDay, ZonedDateTime endOfDay);

    @Query("{ 'userId': ?0, 'status': { $in: ['ACTIVE', 'INACTIVE'] } }")
    List<Shift> findByUserId (int userId);

    @Query("{ 'status': 'ACTIVE', 'date' : { $lt: ?0 } }")
    List<Shift> findActiveShiftsBeforeNow(ZonedDateTime now);
}
