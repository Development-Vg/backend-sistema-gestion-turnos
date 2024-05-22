package edu.uptc.swii.UserQueryService.domain.repository;

import edu.uptc.swii.UserQueryService.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {
    // @Query("{ 'userId' : ?0 }")
    public User findByUserId(Integer idUser);
}
