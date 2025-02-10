package com.bp.usermanagement.controller;

import com.bp.usermanagement.model.User;
import com.bp.usermanagement.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/user")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String healthCheck() {
        return "Working fine...";
    }

    @GetMapping("/get-users")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/add-user")
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

}
