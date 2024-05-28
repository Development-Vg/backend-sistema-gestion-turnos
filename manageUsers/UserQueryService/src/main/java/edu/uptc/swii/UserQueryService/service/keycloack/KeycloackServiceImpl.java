package edu.uptc.swii.UserQueryService.service.keycloack;

import edu.uptc.swii.UserQueryService.domain.model.User;
import edu.uptc.swii.UserQueryService.util.KeycloackProvider;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class KeycloackServiceImpl implements IkeycloakService{

    /**
     * Metodo para listar los usuarios de keycloak
     * @return
     */
    @Override
    public List<UserRepresentation> findAllUsers() {
        return KeycloackProvider.getRealmResource()
                .users()
                .list();
    }
    /**
     * Metodo para un usuario en keycloak por el username
     * @return
     */
    @Override
    public List<UserRepresentation> searchUserByEmail(String email) {
        return KeycloackProvider.getRealmResource()
                .users()
                .searchByEmail(email, true);
    }

    @Override
    public String createUser(@NotNull User user, String password) {
        int status = 0;
        UsersResource usersResource = KeycloackProvider.getUserResource();

        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setFirstName(user.getName());
        userRepresentation.setLastName(user.getLastName());
        userRepresentation.setEmail(user.getEmail());
        userRepresentation.setUsername(user.getEmail().split("@")[0]);
        userRepresentation.setEmailVerified(true); // ideal enviar un correo electronico para verificarlo
        userRepresentation.setEnabled(true);

        Response response = usersResource.create(userRepresentation);
        status = response.getStatus();
        if(status == 201){
            String path = response.getLocation().getPath();
            String userId = path.substring(path.lastIndexOf("/") + 1);

            CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
            credentialRepresentation.setTemporary(false);
            credentialRepresentation.setType(OAuth2Constants.PASSWORD);
            credentialRepresentation.setValue(password);

            usersResource.get(userId).resetPassword(credentialRepresentation);

            RealmResource realmResource = KeycloackProvider.getRealmResource();
            List<RoleRepresentation> roleRepresentations = null;

            if(user.getRoles() == null || user.getRoles().isEmpty()){
                roleRepresentations = List.of(realmResource.roles().get("users-role-TurnsManagementApp").toRepresentation());
            } else {
                roleRepresentations = realmResource.roles()
                        .list()
                        .stream()
                        .filter(role -> user.getRoles()
                                .stream()
                                .anyMatch(roleName -> roleName.equalsIgnoreCase(role.getName())))
                        .toList();
            }
            realmResource.users().get(userId).roles().realmLevel().add(roleRepresentations);
            return "User create successfully";
        }else if(status == 409){
            log.error("User exist already");
            return "usuario ya existe";
        }else {
            log.error("Error creating user");
            return "contacte al administrador";
        }

    }

    @Override
    public void deleteUser(String userId) {
        KeycloackProvider.getUserResource()
                .get(userId)
                .remove();
    }

    @Override
    public void updateUser(String userId, @NotNull User user, String password) {
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setType(OAuth2Constants.PASSWORD);
        credentialRepresentation.setValue(password);

        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setFirstName(user.getName());
        userRepresentation.setLastName(user.getLastName());
        userRepresentation.setEmail(user.getEmail());
        userRepresentation.setUsername(user.getEmail().split("@")[0]);
        userRepresentation.setEmailVerified(true); // ideal enviar un correo electronico para verificarlo
        userRepresentation.setEnabled(true);
        userRepresentation.setCredentials(Collections.singletonList(credentialRepresentation));

        UserResource userResource = KeycloackProvider.getUserResource().get(userId);
        userResource.update(userRepresentation);
    }
}
