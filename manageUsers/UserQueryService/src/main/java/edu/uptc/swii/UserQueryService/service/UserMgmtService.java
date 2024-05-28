package edu.uptc.swii.UserQueryService.service;

import edu.uptc.swii.UserQueryService.domain.model.User;

import java.util.List;

public interface UserMgmtService {
    public void saveUser(User user);
    public User findByUserId(int userId);
    public User userIdByEmail(String email);
    // public void deleteUser(String userId);
    public List<User> listAllUser();
}
