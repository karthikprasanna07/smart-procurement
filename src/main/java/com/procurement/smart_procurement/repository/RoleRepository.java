package com.procurement.smart_procurement.repository;

import com.procurement.smart_procurement.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// Repository to access Role table
public interface RoleRepository extends JpaRepository<Role, Long> {

    // Find role by name like ROLE_USER or ROLE_ADMIN
    Optional<Role> findByName(String name);
}
