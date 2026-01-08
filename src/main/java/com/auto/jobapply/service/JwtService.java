package com.auto.jobapply.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    private static final Logger logger = LoggerFactory.getLogger(JwtService.class);

    // IMPORTANT: In production, move this to application.properties or environment variables
    private static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
    private static final long EXPIRATION_TIME = 86400000; // 24 hours in milliseconds

    /**
     * Generates a token for a specific username
     */
    public String generateToken(String username) {
        try {
            Map<String, Object> claims = new HashMap<>();
            return createToken(claims, username);
        } catch (Exception e) {
            logger.error("Failed to generate JWT token for user {}: {}", username, e.getMessage());
            throw new RuntimeException("Token generation failed", e);
        }
    }

    private String createToken(Map<String, Object> claims, String username) {
        try {
            return Jwts.builder()
                    .claims(claims)
                    .subject(username)
                    .issuedAt(new Date(System.currentTimeMillis()))
                    .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .signWith(getSigningKey())
                    .compact();
        } catch (Exception e) {
            logger.error("Error during JWT compacting process: {}", e.getMessage());
            throw e;
        }
    }

    /**
     * Extracts the signing key from the Base64 encoded secret
     */
    private SecretKey getSigningKey() {
        try {
            byte[] keyBytes = Decoders.BASE64.decode(SECRET);
            return Keys.hmacShaKeyFor(keyBytes);
        } catch (IllegalArgumentException e) {
            logger.error("Invalid Secret Key format (not Base64): {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error retrieving signing key: {}", e.getMessage());
            throw e;
        }
    }
}