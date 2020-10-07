package com.sylleryum.meajudaaajudar.security;


import java.time.LocalDate;

public class JWTConfig {

    private static final LocalDate CURRENT_TIME = LocalDate.now();
    public static final java.sql.Date EXPIRE_ADMIN_DAYS = java.sql.Date.valueOf(CURRENT_TIME.now().plusDays(1));
    public static final java.sql.Date EXPIRE_USER = java.sql.Date.valueOf(CURRENT_TIME.now().plusDays(365));
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    //expired token for test:
    //eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VybmFtZTMiLCJyb2xlIjoiUk9MRV9BRE1JTiIsImV4cCI6MTYwMTY5NDAwMH0.RTrsDsskU0r-nsI1Da79xuB_NdkDON3FyFhn8VmNbjl0JDWj1aDzQKv7FqWcWicBMIxLi4alXjm4amTb1lkPqQ
}
