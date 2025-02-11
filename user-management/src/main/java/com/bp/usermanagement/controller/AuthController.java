package com.bp.usermanagement.controller;

import com.bp.usermanagement.model.User;
import com.bp.usermanagement.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public boolean login(@RequestBody User user) {
        return authService.login(user.getEmail(), user.getPassword());
    }
}
