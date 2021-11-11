package com.netcracker.demo.controller;

import com.netcracker.demo.model.User;
import com.netcracker.demo.service.KeycloakAdminClientService;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class ClientController {

//    @Autowired
//    KeycloakAdminClientService keycloakAdminClientService;
//
//    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
//    public UserRepresentation createUser(@RequestBody User user) {
//        return keycloakAdminClientService.addUser(user);
//    }
}
