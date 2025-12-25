package com.procurement.smart_procurement.service;

import com.procurement.smart_procurement.entity.AuthUser;

import java.util.List;

public interface AuthUserService {
    AuthUser createUser(AuthUser user);
    List<AuthUser> getAllUsers();
}
