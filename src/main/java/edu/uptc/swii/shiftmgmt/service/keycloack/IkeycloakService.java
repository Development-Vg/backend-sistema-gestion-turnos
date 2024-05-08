package edu.uptc.swii.shiftmgmt.service.keycloack;

import edu.uptc.swii.shiftmgmt.controller.dto.UserDTO;

import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;

public interface IkeycloakService {
    List<UserRepresentation> findAllUsers();
    List<UserRepresentation> searchUserByUserName(String username);
    String createUser(UserDTO userDTO);
    void deleteUser(String userId);
    void updateUser(String userId, UserDTO userDTO);
}
