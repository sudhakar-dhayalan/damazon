package com.damazon.backend.controllers;

import com.damazon.backend.model.User;
import com.damazon.backend.service.JwtService;
import com.damazon.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @GetMapping("login")
    public String login(@RequestBody User user) {
        // username must be case-insensitive
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUserName().toLowerCase(), user.getPassword())
        );

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUserName());
        } else {
            return "Failure";
        }
    }
}
