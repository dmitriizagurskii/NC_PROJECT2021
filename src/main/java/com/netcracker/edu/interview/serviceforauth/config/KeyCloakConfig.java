package com.netcracker.edu.interview.serviceforauth.config;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeyCloakConfig {
    final static String serverUrl = "http://localhost:8080/auth";
    final public static String realm = "Netcracker";
    final static String clientId = "my_client";
    final static String clientSecret = "ee42617f-f17e-4268-bb81-3396fd832ce8";
    final static String userName = "admin";
    final static String password = "admin";

    @Bean
    public Keycloak keycloak(){
            return KeycloakBuilder.builder()
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
}
