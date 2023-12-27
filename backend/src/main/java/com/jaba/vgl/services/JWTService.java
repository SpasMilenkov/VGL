package com.jaba.vgl.services;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JWTService  {

    String extractUserName(String token);
    String generateAccessToken(UserDetails userDetails);
    boolean isTokenValid(String token, UserDetails userDetails);
    boolean isTokenExpired(String token);
    String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails);
}
