package com.procurement.smart_procurement.controller;

import com.procurement.smart_procurement.entity.AuthUser;
import com.procurement.smart_procurement.service.AuthUserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;


import java.util.List;

@RestController
@RequestMapping("/api/users")
public class AuthUserController {

    private final AuthUserService authUserService;

    public AuthUserController(AuthUserService authUserService) {
        this.authUserService = authUserService;
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping
    public AuthUser createUser(@RequestBody AuthUser user) {
        return authUserService.createUser(user);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping
    public List<AuthUser> getAllUsers() {
        return authUserService.getAllUsers();
    }
}
