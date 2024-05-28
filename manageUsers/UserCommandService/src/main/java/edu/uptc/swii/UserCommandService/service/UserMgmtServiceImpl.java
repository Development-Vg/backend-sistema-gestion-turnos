package edu.uptc.swii.UserCommandService.service;

import edu.uptc.swii.UserCommandService.domain.model.User;
import edu.uptc.swii.UserCommandService.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserMgmtServiceImpl implements UserMgmtService{
    @Autowired
    UserRepository userRepo;

    @Override
    public void saveUser(User user){
        userRepo.save(user);
    }



    @Override
    public User findByUserDocument(String document) {
        return userRepo.findByDocument(document);
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
