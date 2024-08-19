package org.example.serve.controllers;

import org.example.serve.model.User;
import org.example.serve.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User loginRequest) {
        Optional<User> user = userService.findByEmail(loginRequest.getEmail());

        if (user.isPresent() && userService.checkPassword(user.get(), loginRequest.getPassword())) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }

    @PostMapping("/signup_user")
    public ResponseEntity<String> signup(@RequestBody User signupRequest) {
        // Check if the user already exists
        if (userService.findByEmail(signupRequest.getEmail()).isPresent()) {
            return ResponseEntity.status(400).body("Email already in use");
        }

        // Set role as admin or user
        signupRequest.setRole("ADMIN"); // or use a boolean isAdmin
        // Save the new user
        userService.saveUser(signupRequest);
        return ResponseEntity.ok("User registered successfully");
    }



}
