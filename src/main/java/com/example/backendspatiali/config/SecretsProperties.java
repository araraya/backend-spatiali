package com.example.backendspatiali.config;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("secrets")
public class SecretsProperties {

    private final String SECRET;

    private final String ENCRYPTION;

    public SecretsProperties(String secret, String encryption) {
        SECRET = secret;
        ENCRYPTION = encryption;
    }

    public String getENCRYPTION() {return  ENCRYPTION;}

    public String getSECRET() {
        return SECRET;
    }
}
