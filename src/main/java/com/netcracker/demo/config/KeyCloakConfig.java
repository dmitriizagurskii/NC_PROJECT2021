package com.netcracker.demo.config;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;

public class KeyCloakConfig {
    static Keycloak keycloak = null;
    final static String serverUrl = "http://localhost:8080/auth";
    final public static String realm = "Netcracker";
    final static String clientId = "my_client";
    final static String clientSecret = "ee42617f-f17e-4268-bb81-3396fd832ce8";
    final static String userName = "admin";
    final static String password = "admin";

    public void KeycloakConfig() {
    }

    public static Keycloak getInstance(){
        if(keycloak == null){

            keycloak = KeycloakBuilder.builder()
                    .serverUrl(serverUrl)
                    .realm(realm)
                    .grantType(OAuth2Constants.PASSWORD)
                    .username(userName)
                    .password(password)
                    .clientId(clientId)
                    .clientSecret(clientSecret)
                    .resteasyClient(new ResteasyClientBuilder()
                            .connectionPoolSize(10)
                            .build())
                    .build();
        }
        return keycloak;
    }
}
