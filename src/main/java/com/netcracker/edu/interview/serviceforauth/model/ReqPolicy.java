package com.netcracker.edu.interview.serviceforauth.model;

public final class ReqPolicy {
    private String resource;
    private String algorithm;

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
