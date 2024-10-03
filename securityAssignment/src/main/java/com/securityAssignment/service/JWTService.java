package com.securityAssignment.service;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JWTService {
    public String generateToken(UserDetails userDetails);
     String extractUsername(String token);
     boolean isTokenValid(String token, UserDetails userDetails);
    String generateRefreshToken(Map<Object,String> claims, UserDetails userDetails);

}
