package com.damazon.backend.service;

import com.damazon.backend.exception.CustomRunTimeException;
import com.damazon.backend.model.User;
import com.damazon.backend.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    public User registerUser(User user) {
        // username must be case-insensitive
        user.setUserName(user.getUserName().toLowerCase());
        if (userRepo.findByUserName(user.getUserName()) != null)
            throw new CustomRunTimeException("User name already exists. Try another name.", "USERNAME_ALREADY_EXISTS", HttpStatus.METHOD_FAILURE);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

}
