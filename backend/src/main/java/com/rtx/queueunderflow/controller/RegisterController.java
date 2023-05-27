package com.rtx.queueunderflow.controller;

import com.rtx.queueunderflow.entity.User;
import com.rtx.queueunderflow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class RegisterController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userService.findByeMail(user.geteMail()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already used");
        }
        user.setPassword(hashPassword(user.getPassword()));
        User newUser = userService.saveUser(user);
        String authToken = generateAuthToken(newUser.getUserId());
        return ResponseEntity.ok(authToken);
    }

    private String generateAuthToken(Long userId) {
        // Implement your authentication token generation logic here
        // Generate a unique authentication token based on the userId
        // Store the token in a cache or database for later verification
        // Return the generated authentication token
        return userId.toString();
    }

    private String hashPassword(String password) {
        return new StringBuilder(password).reverse().toString();
    }
}
