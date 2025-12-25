package com.procurement.smart_procurement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class AuthUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Username used for login
    @Column(nullable = false, unique = true)
    private String username;

    // Encrypted password
    @Column(nullable = false)
    private String password;

    // 🔑 ROLE MAPPING (Many users can have the same role)
    @ManyToOne(fetch = FetchType.EAGER)          // Load role immediately with user
    @JoinColumn(name = "role_id", nullable = false) // FK column in users table
    private Role role;

    // Required by Spring Security
    private boolean enabled = true;

    // Default constructor (JPA needs this)
    public AuthUser() {}

    // Optional constructor (you can use later)
    public AuthUser(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // -------- GETTERS & SETTERS --------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    // 🔑 Role getter (used later by Spring Security)
    public Role getRole() {
        return role;
    }

    // 🔑 Role setter (DO NOT pass String anymore)
    public void setRole(Role role) {
        this.role = role;
    }

    // ✔ Required by Spring Security
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
