package com.procurement.smart_procurement.config;

import com.procurement.smart_procurement.entity.AuthUser;
import com.procurement.smart_procurement.entity.Role;
import com.procurement.smart_procurement.repository.AuthUserRepository;
import com.procurement.smart_procurement.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Profile("!test")
@Component
public class DataLoader implements CommandLineRunner {

    private final AuthUserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder encoder;

    public DataLoader(
            AuthUserRepository userRepo,
            RoleRepository roleRepo,
            PasswordEncoder encoder
    ) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.encoder = encoder;
    }

    @Override
    public void run(String... args) {

        // 🔹 STEP 1: Ensure roles exist (VERY IMPORTANT)
        Role userRole = roleRepo.findByName("ROLE_USER")
                .orElseGet(() -> roleRepo.save(new Role(null, "ROLE_USER")));

        Role adminRole = roleRepo.findByName("ROLE_ADMIN")
                .orElseGet(() -> roleRepo.save(new Role(null, "ROLE_ADMIN")));

        // 🔹 STEP 2: Create normal user
        if (userRepo.findByUsername("testuser").isEmpty()) {
            AuthUser user = new AuthUser();
            user.setUsername("testuser");
            user.setPassword(encoder.encode("pass123"));
            user.setRole(userRole);
            userRepo.save(user);
        }

        // 🔹 STEP 3: Create admin user
        if (userRepo.findByUsername("admin").isEmpty()) {
            AuthUser admin = new AuthUser();
            admin.setUsername("admin");
            admin.setPassword(encoder.encode("admin123"));
            admin.setRole(adminRole);
            userRepo.save(admin);
        }
    }
}
