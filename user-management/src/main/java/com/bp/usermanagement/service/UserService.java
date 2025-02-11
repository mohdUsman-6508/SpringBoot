package com.bp.usermanagement.service;

import com.bp.usermanagement.model.User;
import com.bp.usermanagement.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        //TODO: Spring validation

        if (user.isEmpty()) {
            return;
        }
        userRepository.delete(user.get());
    }

    public User updateUser(User user, String email) {
        Optional<User> existedUser = userRepository.findByEmail(email);
        if (existedUser.isEmpty()) {
            return new User();
        }

        //TODO: spring boot validation
        if (user.getName()!=null) {
            existedUser.get().setName(user.getName());
        }

        if (user.getPassword()!=null) {
            existedUser.get().setPassword(user.getPassword());
        }

        if (user.getRole()!=null) {
            existedUser.get().setRole(user.getRole());
        }

        return userRepository.save(existedUser.get());
    }

}
