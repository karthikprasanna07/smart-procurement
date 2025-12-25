package com.procurement.smart_procurement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    // ✅ REQUIRED by JPA
    public Role() {
    }

    // ✅ REQUIRED by DataLoader
    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // getters & setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
