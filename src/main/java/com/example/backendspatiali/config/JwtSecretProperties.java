package com.example.backendspatiali.config;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("jwtsecret")
public class JwtSecretProperties {

    private final String SECRET;

    public JwtSecretProperties(String secret) {
        SECRET = secret;
    }

    public String getSECRET() {
        return SECRET;
    }
}
