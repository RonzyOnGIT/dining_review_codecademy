package com.diningreview.dining.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diningreview.dining.entities.User;

import com.diningreview.dining.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {


    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }


    @GetMapping()
    public List<User> fetchUsers() {
        return this.userService.getUsers();
    }

    @GetMapping("/{username}")
    public User fetchUser(@PathVariable String username) {
        return this.userService.getUser(username);
    }

    @PostMapping()
    public User createUser(@RequestBody User user) {
        return this.userService.createUser(user);
    }

    @PutMapping()
    public User updateUser(@RequestBody User user) {
        return this.userService.updateUser(user);
    }

    
}
