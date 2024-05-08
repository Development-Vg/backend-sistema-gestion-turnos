package edu.uptc.swii.shiftmgmt.controller;

import edu.uptc.swii.shiftmgmt.service.keycloack.IkeycloakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/keycloack")
@PreAuthorize("hasRole('admin-backend')")
public class KeycloackController {

    @Autowired
    private IkeycloakService ikeycloakService;

    @GetMapping("/search")
    public ResponseEntity<?> findAllUsers(){
        return ResponseEntity.ok(ikeycloakService.findAllUsers());
    }
}
