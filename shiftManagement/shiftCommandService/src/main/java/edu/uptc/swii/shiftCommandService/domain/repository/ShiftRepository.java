package edu.uptc.swii.shiftCommandService.domain.repository;

import edu.uptc.swii.shiftCommandService.domain.model.Shift;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface ShiftRepository extends MongoRepository<Shift, Integer> {
    public Shift findByUserIdAndId(int userId, int id);

    @Query("{ 'date' : { $gte: ?0, $lt: ?1 }, 'status': { $in: ['ACTIVE', 'INACTIVE'] } }")
    List<Shift> findByDateRange(ZonedDateTime startOfDay, ZonedDateTime endOfDay);
}
