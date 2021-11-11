package com.netcracker.demo.model;

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
