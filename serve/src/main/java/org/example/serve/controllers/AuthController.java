package org.example.serve.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.serve.model.User;
import org.example.serve.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody User signupRequest) {
        if (userService.findByEmail(signupRequest.getEmail()).isPresent()) {
            return ResponseEntity.status(400).body("Email already in use");
        }

        // Save the user to the database
        userService.saveUser(signupRequest);
        return ResponseEntity.ok("User registered successfully");
    }
}
