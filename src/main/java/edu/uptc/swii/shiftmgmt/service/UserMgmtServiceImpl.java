package edu.uptc.swii.shiftmgmt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.uptc.swii.shiftmgmt.domain.User;
import edu.uptc.swii.shiftmgmt.repository.UserRepository;


@Service
public class UserMgmtServiceImpl implements UserMgmtService{
    @Autowired
    UserRepository userRepo;

    @Override
    public void saveUser(User user){
        userRepo.save(user);
    }

    // @Override
    // public User findByUserId(String userId) {
    //     return userRepo.findByUserId(userId);
    // }

    // @Override
    // public void deleteUser(String UserId){
    //     User user = userRepo.findByUserId(UserId);
    //     userRepo.delete(user);
    // }

    // @Override
    // public List<User> listAllUser(){
    //     return userRepo.findAll();
    // }

}
