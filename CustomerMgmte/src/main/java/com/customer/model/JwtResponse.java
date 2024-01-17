package com.customer.model;

public class JwtResponse {

    private final String jwtToken;

    public JwtResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    // Getter for jwtToken

    public String getJwtToken() {
        return jwtToken;
    }

}
 
