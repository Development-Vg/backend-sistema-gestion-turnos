package edu.uptc.swii.shiftmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.uptc.swii.shiftmgmt.domain.Credentials;

public interface CredentialRepository extends JpaRepository<Credentials, Integer>{
    
}
