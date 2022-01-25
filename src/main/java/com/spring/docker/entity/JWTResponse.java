package com.spring.docker.entity;

public class JWTResponse {

    private String jwtToken;

    public JWTResponse() {
    }

    public JWTResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
