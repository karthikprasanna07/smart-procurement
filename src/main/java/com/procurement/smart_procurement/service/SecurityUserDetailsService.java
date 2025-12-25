package com.procurement.smart_procurement.service;

import com.procurement.smart_procurement.entity.AuthUser;
import com.procurement.smart_procurement.repository.AuthUserRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;

@Service  // Marks this class as a Spring Security service component
public class SecurityUserDetailsService implements UserDetailsService {

    // Repository used to fetch user details from the database
    private final AuthUserRepository authUserRepository;

    // Constructor-based dependency injection
    public SecurityUserDetailsService(AuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }

    /**
     * This method is automatically called by Spring Security
     * whenever a user tries to authenticate (login or JWT validation).
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Step 1: Fetch user details from database using username
        // If user does not exist, Spring Security throws authentication failure
        AuthUser u = authUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        // Step 2: Convert application's Role entity into Spring Security authority
        // Example: ROLE_ADMIN / ROLE_USER
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(u.getRole().getName());

        // Debug log to verify which user and role are loaded during authentication
        // (Useful during development and testing)

        // Step 3: Build Spring Security's internal User object
        // This object is stored in SecurityContext and used for authorization checks
        return org.springframework.security.core.userdetails.User
                .withUsername(u.getUsername())       // Username used for authentication
                .password(u.getPassword())           // Encrypted password from DB
                .authorities(List.of(authority))     // Assign role as authority
                .accountLocked(false)                 // Account is not locked
                .disabled(!u.isEnabled())             // Disable login if user is inactive
                .build();
    }
}
