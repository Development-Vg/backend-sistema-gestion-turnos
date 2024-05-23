package edu.uptc.swii.UserQueryService.controller;


import edu.uptc.swii.UserQueryService.domain.model.User;
import edu.uptc.swii.UserQueryService.service.UserMgmtService;
import edu.uptc.swii.UserQueryService.service.keycloack.IkeycloakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.security.core.Authentication;


import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/listUsers")
public class UserController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPICSUSERS= "Usernot1";
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
    //@PreAuthorize("hasRole('admin_backen_role') or hasRole('user_backen_role')")
    public String helloAdmin(Authentication authentication){
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String role = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .filter(r -> r.equals("ROLE_admin_backen_role") || r.equals("ROLE_user_backen_role"))
                .findFirst()
                .orElse("No Role Found");
        return role;
    }

    @GetMapping("/hello-2")
    @PreAuthorize("hasRole('admin')") //or hashRole()
    public String helloUser(){
        return "Hello USER";
    }

    @RequestMapping(value = "/listAll", method = RequestMethod.GET, produces = "application/json")
    public List<User> listUsers(){
        return userMgmtService.listAllUser();
    }

    @RequestMapping(value = "/listAllKeycloack", method = RequestMethod.GET, produces = "application/json")
    public List<UserRepresentation> listUsersKeycloak(){
        return ikeycloakService.findAllUsers();
    }

    //@RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = "application/json")
    @KafkaListener(topics = "Usernot", groupId = "myGroup")
    public void findUserById(String message){
        User user = userMgmtService.findByUserId(Integer.parseInt(message));
        System.out.println(user.getEmail());
        kafkaTemplate.send(TOPICSUSERS, user.getEmail() + "-" + user.getCelphone());
        user = null;
    }

}
