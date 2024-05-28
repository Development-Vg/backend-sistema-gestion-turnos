package edu.uptc.swii.UserQueryService.controller;


import edu.uptc.swii.UserQueryService.domain.model.User;
import edu.uptc.swii.UserQueryService.service.UserMgmtService;
import edu.uptc.swii.UserQueryService.service.keycloack.IkeycloakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.keycloak.representations.idm.UserRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;

@RestController
@RequestMapping("/listUsers")
public class UserController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPICSUSERS= "Usernot1";
    private static final String TOPICSUSERSCREATE= "userCreate";
    @Autowired
    private UserMgmtService userMgmtService;

    @Autowired
    private IkeycloakService ikeycloakService;

    @Value("${message}")
    private String message;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public String welcome(){
        return message;
    }

    @GetMapping("/login")
    @PreAuthorize("hasRole('admin_backen_role') or hasRole('user_backen_role')")
    public ResponseEntity loginUser(@RequestParam String email){
        User user = userMgmtService.userIdByEmail(email);
        if(user == null){
            System.out.println(email);
            UserRepresentation userRepresentation =  ikeycloakService.searchUserByEmail(email).get(0);
            kafkaTemplate.send(TOPICSUSERSCREATE, userRepresentation.getFirstName() + "\n" +
                    userRepresentation.getLastName() + "\n" +
                    userRepresentation.getEmail() + "\n" );
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
            user = userMgmtService.userIdByEmail(email);
        }
        return new ResponseEntity<>(user.getId(), HttpStatus.OK);
    }

    @GetMapping("/hello-2")
    @PreAuthorize("hasRole('admin')") //or hashRole()
    public String helloUser(){
        return "Hello USER";
    }

    @PreAuthorize("hasRole('admin_backen_role')")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET, produces = "application/json")
    public List<User> listUsers(){
        return userMgmtService.listAllUser();
    }

    @PreAuthorize("hasRole('admin_backen_role')")
    @RequestMapping(value = "/listAllKeycloack", method = RequestMethod.GET, produces = "application/json")
    public List<UserRepresentation> listUsersKeycloak(){
        return ikeycloakService.findAllUsers();
    }

    //@RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = "application/json")
    @KafkaListener(topics = "Usernot", groupId = "myGroup")
    public void findUserById(String message){
        User user = userMgmtService.findByUserId(Integer.parseInt(message));
        kafkaTemplate.send(TOPICSUSERS, user.getEmail() + "\n" + user.getName()+ " " + user.getLastName());
        user = null;
    }

}
