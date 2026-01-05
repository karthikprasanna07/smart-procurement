package com.procurement.smart_procurement.config;

import com.procurement.smart_procurement.security.JwtAuthenticationFilter;
import com.procurement.smart_procurement.util.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;



@Configuration
@EnableMethodSecurity(jsr250Enabled = true)// Marks this class as a Spring Security configuration class
public class SecurityConfig {

    // Service that loads user details (username, password, role) from DB
    private final UserDetailsService userDetailsService;

    // Utility class for JWT creation and validation
    private final JwtUtil jwtUtil;

    // Custom filter that validates JWT for every request
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    // Constructor-based dependency injection
    public SecurityConfig(
            UserDetailsService userDetailsService,
            JwtUtil jwtUtil,
            JwtAuthenticationFilter jwtAuthenticationFilter
    ) {
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    // Password encoder bean used to hash passwords (BCrypt is industry standard)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // AuthenticationManager is required to authenticate username/password during login
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration cfg) throws Exception {
        return cfg.getAuthenticationManager();
    }

    // Main Spring Security configuration
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        http
                // Disable CSRF because we are using JWT (stateless authentication)
                .csrf(csrf -> csrf.disable())

                // Configure application as STATELESS (no HTTP sessions)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // Define authorization (access control) rules
                .authorizeHttpRequests(auth -> auth

                        // ✅ Allow Swagger UI and OpenAPI documentation without login
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/v3/api-docs/**"
                        ).permitAll()

                        // ✅ Allow login API (user must be able to login without token)
                        .requestMatchers("/auth/login").permitAll()

                        // 🔐 RBAC CHECK
                        // Only ADMIN users can access vendor-related APIs
                        .requestMatchers("/api/v1/vendors/**")
                        .hasAuthority("ROLE_ADMIN")

                        // ✅ TEMPORARY: Allow Purchase Order APIs without authentication
                        // This is done ONLY to isolate the 403 issue during Sprint 5 testing
                        .requestMatchers("/api/pos/**").permitAll()
                        .requestMatchers("/api/pos").permitAll()


                        // Allow ALL remaining requests without authentication
                        // This helps confirm whether 403 is coming from Spring Security rules
                        .anyRequest().permitAll()
                )

                // Add JWT filter before Spring Security's default authentication filter
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
                .requestMatchers("/api/pos", "/api/pos/**");
    }

}