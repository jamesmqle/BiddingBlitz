package com.example.backend.service;

import com.example.backend.dto.UserDetailsDTO;
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
public class UserService implements UserServiceInterface {

    private final UserCredentialsRepository userCredentialsRepository;
    private final UserInfoRepository userInfoRepository;
    private final UserAddressRepository userAddressRepository;

    @Autowired
    public UserService(UserCredentialsRepository userCredentialsRepository,
                       UserInfoRepository userInfoRepository,
                       UserAddressRepository userAddressRepository) {
        this.userCredentialsRepository = userCredentialsRepository;
        this.userInfoRepository = userInfoRepository;
        this.userAddressRepository = userAddressRepository;
    }

    @Override
    @Transactional
    public void registerUser(UserCredentials userCredentials, UserInfo userInfo, UserAddress userAddress) {
        UserInfo savedUserInfo = userInfoRepository.save(userInfo);
        userCredentials.setUserId(savedUserInfo.getUserId());
        userCredentialsRepository.save(userCredentials);
        userAddress.setUserInfo(savedUserInfo);
        userAddressRepository.save(userAddress);
    }

    @Override
    public Long authenticateUser(UserCredentials userCredentials) {
        UserCredentials existingUser = userCredentialsRepository.findByUsername(userCredentials.getUsername());
        if (existingUser != null && existingUser.getPassword().equals(userCredentials.getPassword())) {
            return existingUser.getUserId();
        }
        return null;
    }

    @Override
    public UserDetailsDTO getUserDetails(Long userId) {
        UserInfo userInfo = userInfoRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserAddress userAddress = userAddressRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User address not found"));

        return new UserDetailsDTO(userInfo, userAddress);
    }
}
