package com.procurement.smart_procurement.service.impl;

import com.procurement.smart_procurement.entity.AuthUser;
import com.procurement.smart_procurement.repository.AuthUserRepository;
import com.procurement.smart_procurement.service.AuthUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthUserServiceImpl implements AuthUserService {

    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthUserServiceImpl(AuthUserRepository authUserRepository,
                               PasswordEncoder passwordEncoder) {
        this.authUserRepository = authUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthUser createUser(AuthUser user) {

        // 🔐 IMPORTANT: BCrypt encode password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return authUserRepository.save(user);
    }

    @Override
    public List<AuthUser> getAllUsers() {
        return authUserRepository.findAll();
    }
}
