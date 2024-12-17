package com.example.backend.service;

import com.example.backend.dto.UserDetailsDTO;
import com.example.backend.model.authentication.UserCredentials;
import com.example.backend.model.user.UserAddress;
import com.example.backend.model.user.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceProxy implements UserServiceInterface {

    private final UserService userService;

    @Autowired
    public UserServiceProxy(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void registerUser(UserCredentials userCredentials, UserInfo userInfo, UserAddress userAddress) {
        System.out.println("Proxy: Logging registration process...");
        userService.registerUser(userCredentials, userInfo, userAddress);
        System.out.println("Proxy: Registration successful.");
    }

    @Override
    public Long authenticateUser(UserCredentials userCredentials) {
        System.out.println("Proxy: Logging authentication attempt...");
        Long userId = userService.authenticateUser(userCredentials);
        if (userId != null) {
            System.out.println("Proxy: Authentication successful for userId: " + userId);
        } else {
            System.out.println("Proxy: Authentication failed.");
        }
        return userId;
    }

    @Override
    public UserDetailsDTO getUserDetails(Long userId) {
        System.out.println("Proxy: Logging retrieval of user details...");
        return userService.getUserDetails(userId);
    }
}
