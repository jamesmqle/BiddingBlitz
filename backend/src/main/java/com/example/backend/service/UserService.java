package com.example.backend.service;

import com.example.backend.model.authentication.UserCredentials;
import com.example.backend.dto.UserDetailsDTO;
import com.example.backend.model.user.UserAddress;
import com.example.backend.model.user.UserInfo;
import com.example.backend.repository.authentication.UserCredentialsRepository;
import com.example.backend.repository.user.UserAddressRepository;
import com.example.backend.repository.user.UserInfoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserCredentialsRepository userCredentialsRepository;
    private final UserInfoRepository userInfoRepository;
    private final UserAddressRepository userAddressRepository;

    @Autowired
    public UserService(UserCredentialsRepository userCredentialsRepository, UserInfoRepository userInfoRepository, UserAddressRepository userAddressRepository) {
        this.userCredentialsRepository = userCredentialsRepository;
        this.userInfoRepository = userInfoRepository;
        this.userAddressRepository = userAddressRepository;
    }

    @Transactional
    public void registerUser(UserCredentials userCredentials, UserInfo userInfo, UserAddress userAddress) {
        UserInfo savedUserInfo = userInfoRepository.save(userInfo); // Save UserInfo first to generate the userId
        userCredentials.setUserId(savedUserInfo.getUserId()); // Associate credentials with userId, not UserInfo object
        userCredentialsRepository.save(userCredentials); // Save the credentials in the authentication database
        userAddress.setUserInfo(savedUserInfo); // This still links UserInfo to address in user_db
        userAddressRepository.save(userAddress); // Save the address in the user database
    }

    public Long authenticateUser(UserCredentials userCredentials) {
        // Find the user by username
        UserCredentials existingUser = userCredentialsRepository.findByUsername(userCredentials.getUsername());

        // Check if user exists and password matches
        if (existingUser != null && existingUser.getPassword().equals(userCredentials.getPassword())) {
            // If authentication is successful, return the userId
            return existingUser.getUserId();
        }

        // If authentication fails, return null
        return null;
    }

    public UserDetailsDTO getUserDetails(Long userId) {
        UserInfo userInfo = userInfoRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserAddress userAddress = userAddressRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User address not found"));

        return new UserDetailsDTO(userInfo, userAddress);
    }

}
