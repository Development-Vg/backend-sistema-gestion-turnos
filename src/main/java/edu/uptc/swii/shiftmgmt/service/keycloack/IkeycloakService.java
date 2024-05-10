package edu.uptc.swii.shiftmgmt.service.keycloack;

import edu.uptc.swii.shiftmgmt.controller.dto.UserDTO;

import edu.uptc.swii.shiftmgmt.domain.model.User;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;

public interface IkeycloakService {
    List<UserRepresentation> findAllUsers();
    List<UserRepresentation> searchUserByUserName(String username);
    String createUser(User user, String password);
    void deleteUser(String userId);
    void updateUser(String userId, User user, String password);
}
