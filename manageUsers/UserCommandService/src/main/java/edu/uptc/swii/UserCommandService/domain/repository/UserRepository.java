package edu.uptc.swii.UserCommandService.domain.repository;

import edu.uptc.swii.UserCommandService.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {
    // @Query("{ 'userId' : ?0 }")
    User findByDocument(String document);
}
