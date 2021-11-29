package com.netcracker.edu.interview.serviceforauth.model;

public final class RespPolicy {
    String allow;

    public RespPolicy() {
    }

    public RespPolicy(String allow) {
        this.allow = allow;
    }

    public String getAllow() {
        return allow;
    }

    public void setAllow(String allow) {
        this.allow = allow;
    }
}
