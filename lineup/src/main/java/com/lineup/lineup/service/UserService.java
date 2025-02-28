package com.lineup.lineup.service;

import com.lineup.lineup.exception.ResourceNotFoundException;
import com.lineup.lineup.model.User;
import com.lineup.lineup.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new ResourceNotFoundException("User not found with id " + id);
        }
    }

    public User createUser(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            return existingUser.get();
        } else {
            return userRepository.save(user);
        }
    }

    public User updateUser(User user, Long id) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            if (user.getEmail() != null) {
                existingUser.get().setEmail(user.getEmail());
            }
            if (user.getUsername() != null) {
                existingUser.get().setUsername(user.getUsername());
            }
            if (user.getPassword() != null) {
                existingUser.get().setPassword(user.getPassword());
            }
            return userRepository.save(existingUser.get());
        }

        throw new ResourceNotFoundException("User not found with " + user.getId());
    }

    public void deleteUser(Long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("User not found with id " + id);
        }
    }


}
