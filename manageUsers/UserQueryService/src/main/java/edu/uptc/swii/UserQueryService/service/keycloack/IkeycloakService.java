package edu.uptc.swii.UserQueryService.service.keycloack;

import edu.uptc.swii.UserQueryService.domain.model.User;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;

public interface IkeycloakService {
    List<UserRepresentation> findAllUsers();
    List<UserRepresentation> searchUserByEmail(String email);
    String createUser(User user, String password);
    void deleteUser(String userId);
    void updateUser(String userId, User user, String password);
}
