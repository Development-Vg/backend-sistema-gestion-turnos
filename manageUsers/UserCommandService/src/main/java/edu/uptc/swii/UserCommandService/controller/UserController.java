package edu.uptc.swii.UserCommandService.controller;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import edu.uptc.swii.UserCommandService.service.keycloack.IkeycloakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.uptc.swii.UserCommandService.domain.model.User;
import edu.uptc.swii.UserCommandService.service.UserMgmtService;


@RestController
@RequestMapping("/users")
public class UserController {
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

    @GetMapping("/hello-1")
    @PreAuthorize("hasRole('admin-backend')")
    public String helloAdmin(){
        return "Hello ADMIN";
    }

    @GetMapping("/hello-2")
    @PreAuthorize("hasRole('users-backend')") //or hashRole()
    public String helloUser(){
        return "Hello USER";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity createUser(@RequestBody Map<String, Object> requestData) {
        if(userMgmtService.findByUserDocument((String) requestData.get("document")) == null) {
            User user = new User((String) requestData.get("name"), (String) requestData.get("lastName"), (String) requestData.get("typeDocument"),
                    (String) requestData.get("document"), (String) requestData.get("addres"), (String) requestData.get("email"), (String) requestData.get("celphone"));
            user.getRoles().add(((String) requestData.get("typeUser")).equals("U") ? "user" : "admin");
            if (ikeycloakService.createUser(user, (String) requestData.get("password")) == 201){
                userMgmtService.saveUser(user);
                return new ResponseEntity<>("Registro exitoso " + user.getId(), HttpStatus.OK);
            }
            return new ResponseEntity<>("Ya esiste ese email", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Ya existe ese documento ", HttpStatus.NOT_FOUND);
    }

    @KafkaListener(topics = "userCreate", groupId = "myGroup")
    public void createUseInexistPosgrets(String message){
        String [] userKeycloack = message.split("\n");
        for (int i = 0; i < userKeycloack.length; i++ ){
            System.out.println(userKeycloack[i]);
        }
       User user = new User();
       user.setName(userKeycloack[0]);
       user.setLastName(userKeycloack[1]);
       user.setEmail(userKeycloack[2]);
       user.setRoles(new LinkedHashSet<>());
       user.getRoles().add("user");
       userMgmtService.saveUser(user);
    }
}
