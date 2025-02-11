package com.bp.usermanagement.service;

import com.bp.usermanagement.model.User;
import com.bp.usermanagement.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean login(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.map(u -> u.getPassword().equals(password)).orElse(false);
    }
}

