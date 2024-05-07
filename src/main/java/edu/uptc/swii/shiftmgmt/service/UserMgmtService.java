package edu.uptc.swii.shiftmgmt.service;

import edu.uptc.swii.shiftmgmt.domain.model.Credentials;
import edu.uptc.swii.shiftmgmt.domain.model.User;

public interface UserMgmtService {
    public void saveUser(User user);
    public void saveCredential(Credentials credentials);
    // public User findByUserId(String userId);
    // public void deleteUser(String userId);
    // public List<User> listAllUser();
}
