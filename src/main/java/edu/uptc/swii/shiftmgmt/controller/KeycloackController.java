package edu.uptc.swii.shiftmgmt.controller;

import edu.uptc.swii.shiftmgmt.controller.dto.UserDTO;
import edu.uptc.swii.shiftmgmt.service.keycloack.IkeycloakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/keycloack")
//@PreAuthorize("hasRole('admin-backend')")
public class KeycloackController {

//    @Autowired
//    private IkeycloakService ikeycloakService;
//
//    @GetMapping("/search")
//    public ResponseEntity<?> findAllUsers(){
//        return ResponseEntity.ok(ikeycloakService.findAllUsers());
//    }
//
//    @PostMapping("/create")
//    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) throws URISyntaxException {
//        String response = ikeycloakService.createUser(userDTO);
//        return ResponseEntity.created(new URI("/keycloak/create")).body(response);
//    }
}
