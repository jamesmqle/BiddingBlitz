package com.example.backend.service;

import com.example.backend.model.authentication.UserCredentials;
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

    public boolean authenticateUser(UserCredentials userCredentials) {
        UserCredentials existingUser = userCredentialsRepository.findByUsername(userCredentials.getUsername());
        return existingUser.getPassword().equals(userCredentials.getPassword()); // If user is found and password matches
    }

}
