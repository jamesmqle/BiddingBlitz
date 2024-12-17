package com.example.backend.service;

import com.example.backend.dto.UserDetailsDTO;
import com.example.backend.model.authentication.UserCredentials;
import com.example.backend.model.user.UserAddress;
import com.example.backend.model.user.UserInfo;

public interface UserServiceInterface {
    void registerUser(UserCredentials userCredentials, UserInfo userInfo, UserAddress userAddress);
    Long authenticateUser(UserCredentials userCredentials);
    UserDetailsDTO getUserDetails(Long userId);
}
