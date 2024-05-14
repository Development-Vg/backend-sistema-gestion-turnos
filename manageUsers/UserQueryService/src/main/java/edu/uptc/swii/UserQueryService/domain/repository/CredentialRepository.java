package edu.uptc.swii.UserQueryService.domain.repository;

import edu.uptc.swii.UserQueryService.domain.model.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CredentialRepository extends JpaRepository<Credentials, Integer>{
    
}
