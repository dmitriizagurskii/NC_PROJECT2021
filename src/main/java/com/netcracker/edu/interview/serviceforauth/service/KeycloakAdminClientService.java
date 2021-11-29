package com.netcracker.edu.interview.serviceforauth.service;

import com.netcracker.edu.interview.serviceforauth.config.KeyCloakConfig;
import com.netcracker.edu.interview.serviceforauth.model.User;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class KeycloakAdminClientService {

    @Autowired
    KeyCloakConfig keyCloakConfig;

    public KeycloakAdminClientService() {
    }

    public UserRepresentation addUser(User user) {
        //UsersResource usersResource = KeyCloakConfig.getInstance().realm(KeyCloakConfig.realm).users();
        UsersResource usersResource = keyCloakConfig.keycloak().realm(KeyCloakConfig.realm).users();
        CredentialRepresentation credentialRepresentation = createPasswordCredentials(user.getPassword());

        UserRepresentation kcUser = new UserRepresentation();
        kcUser.setUsername(user.getEmail());
        kcUser.setCredentials(Collections.singletonList(credentialRepresentation));
        kcUser.setFirstName(user.getFirstName());
        kcUser.setLastName(user.getLastName());
        kcUser.setEmail(user.getEmail());
        kcUser.setEnabled(true);
        kcUser.setEmailVerified(false);
        usersResource.create(kcUser);

        return kcUser;
    }

    private static CredentialRepresentation  createPasswordCredentials(String password) {
        CredentialRepresentation passwordCredentials = new CredentialRepresentation();
        passwordCredentials.setTemporary(false);
        passwordCredentials.setType(CredentialRepresentation.PASSWORD);
        passwordCredentials.setValue(password);
        return passwordCredentials;
    }
}
