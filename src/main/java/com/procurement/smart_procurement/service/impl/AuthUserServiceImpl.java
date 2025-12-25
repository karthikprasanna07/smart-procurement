package com.procurement.smart_procurement.service.impl;

import com.procurement.smart_procurement.entity.AuthUser;
import com.procurement.smart_procurement.repository.AuthUserRepository;
import com.procurement.smart_procurement.service.AuthUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthUserServiceImpl implements AuthUserService {

    private final AuthUserRepository authUserRepository;

    public AuthUserServiceImpl(AuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }

    @Override
    public AuthUser createUser(AuthUser user) {
        return authUserRepository.save(user);
    }

    @Override
    public List<AuthUser> getAllUsers() {
        return authUserRepository.findAll();
    }
}
