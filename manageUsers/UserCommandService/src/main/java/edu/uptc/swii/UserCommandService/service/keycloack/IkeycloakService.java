package edu.uptc.swii.UserCommandService.service.keycloack;

import edu.uptc.swii.UserCommandService.domain.model.User;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;

public interface IkeycloakService {
    List<UserRepresentation> findAllUsers();
    List<UserRepresentation> searchUserByUserName(String username);
    int createUser(User user, String password);
    void deleteUser(String userId);
    void updateUser(String userId, User user, String password);
}
