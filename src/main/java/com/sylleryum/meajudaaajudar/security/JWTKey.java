package com.sylleryum.meajudaaajudar.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

//@PropertySource("classpath:application.properties")
@Configuration
public class JWTKey {

    @Value("${application.jwt.secretKey}")
    private String secretKey;

    public String getSecretKey() {
        return secretKey;
    }
}
