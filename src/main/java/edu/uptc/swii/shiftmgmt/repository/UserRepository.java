package edu.uptc.swii.shiftmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.uptc.swii.shiftmgmt.domain.User;


public interface UserRepository extends JpaRepository<User, Integer> {
    // @Query("{ 'userId' : ?0 }")
    // public User findByUserId(String userId);
}
