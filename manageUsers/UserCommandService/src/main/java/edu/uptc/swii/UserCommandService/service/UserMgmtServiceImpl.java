package edu.uptc.swii.UserCommandService.service;

import edu.uptc.swii.UserCommandService.domain.model.Credentials;
import edu.uptc.swii.UserCommandService.domain.model.User;
import edu.uptc.swii.UserCommandService.domain.repository.CredentialRepository;
import edu.uptc.swii.UserCommandService.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserMgmtServiceImpl implements UserMgmtService{
    @Autowired
    UserRepository userRepo;
    @Autowired
    CredentialRepository credRepo;

    @Override
    public void saveUser(User user){
        userRepo.save(user);
    }

    @Override
    public void saveCredential(Credentials credentials) {
        credRepo.save(credentials);
    }

//     @Override
//     public User findByUserId(String userId) {
//         return userRepo.findByUserId(userId);
//     }

    // @Override
    // public void deleteUser(String UserId){
    //     User user = userRepo.findByUserId(UserId);
    //     userRepo.delete(user);
    // }

     @Override
     public List<User> listAllUser(){
         return userRepo.findAll();
     }

}
