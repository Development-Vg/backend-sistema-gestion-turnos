package edu.uptc.swii.UserQueryService.controller;


import edu.uptc.swii.UserQueryService.domain.model.User;
import edu.uptc.swii.UserQueryService.service.UserMgmtService;
import edu.uptc.swii.UserQueryService.service.keycloack.IkeycloakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.security.core.Authentication;


import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/listUsers")
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

    @GetMapping("/login")
    @PreAuthorize("hasRole('admin-backend') or hasRole('users-backend')")
    public String helloAdmin(Authentication authentication){
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String role = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .filter(r -> r.equals("ROLE_admin-backend") || r.equals("ROLE_users-backend"))
                .findFirst()
                .orElse("No Role Found");
        return ""+role;
    }

    @GetMapping("/hello-2")
    @PreAuthorize("hasRole('users-backend')") //or hashRole()
    public String helloUser(){
        return "Hello USER";
    }



    // @RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = "application/json")
    // public User findUserById(@PathVariable("userId") String userId){
    //     User user = userMgmtService.findByUserId(userId);
    //     return user;
    // }
    @RequestMapping(value = "/listAll", method = RequestMethod.GET, produces = "application/json")
    public List<User> listUsers(){
        return userMgmtService.listAllUser();
    }

    @RequestMapping(value = "/listAllKeycloack", method = RequestMethod.GET, produces = "application/json")
    public List<UserRepresentation> listUsersKeycloak(){
        return ikeycloakService.findAllUsers();
    }

}
