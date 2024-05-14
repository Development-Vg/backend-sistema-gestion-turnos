package edu.uptc.swii.UserCommandService.domain.repository;

import edu.uptc.swii.UserCommandService.domain.model.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CredentialRepository extends JpaRepository<Credentials, Integer>{
    
}
