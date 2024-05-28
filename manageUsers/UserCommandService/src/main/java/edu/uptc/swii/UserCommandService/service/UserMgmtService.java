package edu.uptc.swii.UserCommandService.service;

import edu.uptc.swii.UserCommandService.domain.model.User;

import java.util.List;

public interface UserMgmtService {
    public void saveUser(User user);
    public User findByUserDocument(String document);
    // public void deleteUser(String userId);
    public List<User> listAllUser();
}
