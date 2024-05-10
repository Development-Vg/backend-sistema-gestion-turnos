package edu.uptc.swii.shiftmgmt.controller;

import java.util.Map;

import edu.uptc.swii.shiftmgmt.service.keycloack.IkeycloakService;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.uptc.swii.shiftmgmt.domain.model.Credentials;
import edu.uptc.swii.shiftmgmt.domain.model.User;
import edu.uptc.swii.shiftmgmt.service.UserMgmtService;

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
    public String createUser(@RequestBody Map<String, Object> requestData) {
        Credentials credentials = new Credentials();
        credentials.setPassword((String) requestData.get("password"));
        User user = new User((String) requestData.get("name"), (String) requestData.get("lastName"), (String) requestData.get("typeDocument"),
        (String) requestData.get("document"),(String) requestData.get("addres"), (String) requestData.get("email"), (String) requestData.get("celphone"), 
        (String) requestData.get("typeUser"), credentials);
        userMgmtService.saveCredential(credentials);
        userMgmtService.saveUser(user);
        ikeycloakService.createUser(user, credentials.getPassword());
        return "Userid: " + user.getId();
    }

    // @RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = "application/json")
    // public User findUserById(@PathVariable("userId") String userId){
    //     User user = userMgmtService.findByUserId(userId);
    //     return user;
    // }

    // @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json")
    // public String createUser(@RequestBody User user) {
    //     userMgmtService.saveUser(user);
    //     return "Userid: " + user.getId();
    // }

    // @RequestMapping(value = "/listAll", method = RequestMethod.GET, produces = "application/json")
    // public List<User> listUsers(){
    //     return userMgmtService.listAllUser();
    // }

}
