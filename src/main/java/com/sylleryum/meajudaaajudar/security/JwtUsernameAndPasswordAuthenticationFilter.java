package com.sylleryum.meajudaaajudar.security;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sylleryum.meajudaaajudar.commons.TraceIdGenerator;
import com.sylleryum.meajudaaajudar.exception.IlegalCredentialsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.Optional;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.sylleryum.meajudaaajudar.commons.JsonConverter.convertObjectToJson;

public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    static class InnerReadUsernamePassword {
        private String username;
        private String password;
        public InnerReadUsernamePassword() {}
        public String getUsername() {
            return username;
        }
        public void setUsername(String username) {
            this.username = username;
        }
        public String getPassword() {
            return password;
        }
        public void setPassword(String password) {
            this.password = password;
        }
    }

    private final AuthenticationManager authenticationManager;
    private final JWTKey jwtKey;
    private static final Logger logger = LoggerFactory.getLogger(JwtUsernameAndPasswordAuthenticationFilter.class);

    @Autowired
    public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authenticationManager, JWTKey jwtKey) {
        this.jwtKey = jwtKey;
        this.authenticationManager = authenticationManager;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        Optional<String> traceIdHeader = Optional.ofNullable(request.getHeader("traceId"));

        InnerReadUsernamePassword authenticationRequest = null;
        try {
            authenticationRequest = new ObjectMapper().readValue(request.getInputStream(), InnerReadUsernamePassword.class);
            Authentication authentication = new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword());

            Authentication authenticate = authenticationManager.authenticate(authentication);
            System.out.println();
            return authenticate;
        } catch (IOException e) {
            logger.error("Authentication failed for Username: {} ", authenticationRequest.getUsername(), e.getMessage());

//            String traceId = TraceIdGenerator.generateTraceId(traceIdHeader);
//            logger.error("TraceId: {}, Credentials Not Valid", traceId, e);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
//            response.setContentType("application/json");
//            String responseJson = null;
//            try {
//                responseJson = convertObjectToJson(new IlegalTokenException(traceId, e.getMessage()));
//                response.getWriter().write(responseJson);
//            } catch (IOException io) {
//                logger.error("error writing Json", traceId, io);
//                throw new RuntimeException(e);
//
//            }
//            System.out.println();
            throw new RuntimeException(e);
        }

        //return super.attemptAuthentication(request, response);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        ApiUserEntity authenticatedUser = (ApiUserEntity) authResult.getPrincipal();
        Date expiration = authenticatedUser.getRole().equalsIgnoreCase("ROLE_USER") ? JWTConfig.EXPIRE_USER : JWTConfig.EXPIRE_ADMIN_DAYS;

        String token = JWT.create()
                .withSubject(authResult.getName())
                .withExpiresAt(expiration)
                .withClaim("role", authenticatedUser.getRole())
                .sign(HMAC512(jwtKey.getSecretKey()));

        response.addHeader(JWTConfig.AUTHORIZATION_HEADER, JWTConfig.TOKEN_PREFIX+token);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        Optional<String> traceIdHeader = Optional.ofNullable(request.getHeader("traceId"));
        String traceId = TraceIdGenerator.generateTraceId(traceIdHeader);

        try {
            logger.error("TraceId: {}, Access denied", traceId, failed.fillInStackTrace());
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");
            String responseJson = convertObjectToJson(new IlegalCredentialsException(traceId, failed.getLocalizedMessage()));
            response.getWriter().write(responseJson);
            return;
        } catch (Exception e){
            logger.error("TraceId: {}, Other error", traceId, failed.fillInStackTrace());
        }

    }
}
