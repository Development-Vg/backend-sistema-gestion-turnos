package edu.uptc.swii.UserQueryService.service;

import edu.uptc.swii.UserQueryService.domain.model.User;
import edu.uptc.swii.UserQueryService.domain.repository.UserRepository;
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
    public User findByUserId(int id) {
        return userRepo.findById(id);
    }

    @Override
    public User userIdByEmail(String email) {
        return userRepo.findByEmail(email);
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
