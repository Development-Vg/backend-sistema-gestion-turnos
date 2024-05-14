package edu.uptc.swii.UserQueryService.service;

import edu.uptc.swii.UserQueryService.domain.model.Credentials;
import edu.uptc.swii.UserQueryService.domain.model.User;

import java.util.List;

public interface UserMgmtService {
    public void saveUser(User user);
    public void saveCredential(Credentials credentials);
    //public User findByUserId(String userId);
    // public void deleteUser(String userId);
    public List<User> listAllUser();
}
