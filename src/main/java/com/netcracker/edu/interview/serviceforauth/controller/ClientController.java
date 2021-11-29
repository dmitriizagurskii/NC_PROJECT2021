package com.netcracker.edu.interview.serviceforauth.controller;

import com.netcracker.edu.interview.serviceforauth.model.User;
import com.netcracker.edu.interview.serviceforauth.service.KeycloakAdminClientService;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class ClientController {

    @Autowired
    KeycloakAdminClientService keycloakAdminClientService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public UserRepresentation createUser(@RequestBody User user) {
        return keycloakAdminClientService.addUser(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/me")
    public String hi() {
        return "HI";
    }
}
