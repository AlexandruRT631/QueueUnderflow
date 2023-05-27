package com.rtx.queueunderflow.controller;

import com.rtx.queueunderflow.dto.LoginDTO;
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
public class LoginController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginRequest) {
        String email = loginRequest.geteMail();
        String password = loginRequest.getPassword();

        User user = userService.findByeMail(email);

        if (user != null && password.equals(hashPassword(user.getPassword()))) {
            String authToken = generateAuthToken(user.getUserId());
            return ResponseEntity.ok(authToken);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
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
