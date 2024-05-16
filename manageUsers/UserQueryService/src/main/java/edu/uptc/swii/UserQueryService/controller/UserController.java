package edu.uptc.swii.UserQueryService.controller;

import edu.uptc.swii.UserQueryService.domain.model.Credentials;
import edu.uptc.swii.UserQueryService.domain.model.User;
import edu.uptc.swii.UserQueryService.service.UserMgmtService;
import edu.uptc.swii.UserQueryService.service.keycloack.IkeycloakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;


import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/list")
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



    // @RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = "application/json")
    // public User findUserById(@PathVariable("userId") String userId){
    //     User user = userMgmtService.findByUserId(userId);
    //     return user;
    // }
    @RequestMapping(value = "/listAll", method = RequestMethod.GET, produces = "application/json")
    public List<User> listUsers(){
        return userMgmtService.listAllUser();
    }

}
