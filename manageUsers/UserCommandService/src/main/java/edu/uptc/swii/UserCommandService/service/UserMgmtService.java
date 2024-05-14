package edu.uptc.swii.UserCommandService.service;

import edu.uptc.swii.UserCommandService.domain.model.Credentials;
import edu.uptc.swii.UserCommandService.domain.model.User;

import java.util.List;

public interface UserMgmtService {
    public void saveUser(User user);
    public void saveCredential(Credentials credentials);
    //public User findByUserId(String userId);
    // public void deleteUser(String userId);
    public List<User> listAllUser();
}
