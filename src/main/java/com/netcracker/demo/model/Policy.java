package com.netcracker.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "policy")
public final class Policy {
    @Id
    @GeneratedValue
    private Long id;
    private String role_name;
    private String resource;
    private String algorithm;

    public Policy(String role_name, String resource, String algorithm) {
        this.role_name = role_name;
        this.resource = resource;
        this.algorithm = algorithm;
    }

    public Policy() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }
}
