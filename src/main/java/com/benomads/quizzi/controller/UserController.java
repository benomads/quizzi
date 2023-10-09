package com.benomads.quizzi.controller;

import com.benomads.quizzi.entity.User;
import com.benomads.quizzi.model.ApiResponse;
import com.benomads.quizzi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.rmi.server.UID;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();

        return ResponseEntity
            .ok()
            .body(users);
    }


    @PostMapping
    public ResponseEntity<ApiResponse> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);

        return ResponseEntity
            .created(
                URI.create("/api/users" + createdUser.getId()))
            .body(
                new ApiResponse(
                    true,
                    "User created successfully!"));
    }




}