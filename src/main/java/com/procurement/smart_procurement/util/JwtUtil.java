package com.procurement.smart_procurement.util;

import io.jsonwebtoken.*;                     // JWT core classes (build, parse, validate token)
import io.jsonwebtoken.security.Keys;         // Used to create secure secret key
import jakarta.annotation.PostConstruct;      // Runs method after bean creation
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;                // Represents secret key used for signing JWT
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    // This secret key is read from application.properties
    // Example: jwt.secret=some_long_random_string
    @Value("${jwt.secret}")
    private String jwtSecret;

    // Token expiration time in milliseconds
    // Default = 1 hour (3600000 ms) if not mentioned in properties
    @Value("${jwt.expirationMs:1.08e+78}")
    private long jwtExpirationMs;

    // Actual cryptographic key used to sign and verify JWT
    private SecretKey key;

    /**
     * This method runs automatically after Spring creates this bean.
     * It converts the plain secret string into a secure SecretKey.
     */
    @PostConstruct
    public void init() {

        // Safety check: application should not start without JWT secret
        if (jwtSecret == null || jwtSecret.isBlank()) {
            throw new IllegalStateException("jwt.secret must be set in application.properties");
        }

        // Convert secret string into HMAC-SHA key
        // HS256 algorithm requires minimum 32 characters
        key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Generates JWT token for a logged-in user.
     * This token is sent back to client after successful login.
     */
    public String generateToken(String username) {

        // Current time
        Date now = new Date();

        // Token expiry time = now + expiration duration
        Date expiry = new Date(now.getTime() + jwtExpirationMs);

        // Build JWT token
        return Jwts.builder()
                .setSubject(username)          // Store username inside token
                .setIssuedAt(now)              // Token creation time
                .setExpiration(expiry)         // Token expiry time
                .signWith(key, SignatureAlgorithm.HS256) // Sign using secret key
                .compact();                    // Generate final token string
    }

    /**
     * Validates JWT token.
     * Used in JwtAuthenticationFilter for every secured request.
     */
    public boolean validateToken(String token) {
        try {
            // If parsing fails, token is invalid or expired
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);

            return true; // Token is valid
        } catch (JwtException | IllegalArgumentException ex) {
            // Covers expired token, invalid signature, malformed token
            return false;
        }
    }

    /**
     * Extracts username from JWT token.
     * This username is later used to load user details from DB.
     */
    public String extractUsername(String token) {

        // Parse token and get claims (payload data)
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        // Subject stores the username
        return claims.getSubject();
    }
}
