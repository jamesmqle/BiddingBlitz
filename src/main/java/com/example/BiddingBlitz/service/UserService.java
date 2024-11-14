package com.example.BiddingBlitz.service;

import com.example.BiddingBlitz.model.authentication.UserCredentials;
import com.example.BiddingBlitz.model.user.UserAddress;
import com.example.BiddingBlitz.model.user.UserInfo;
import com.example.BiddingBlitz.repository.authentication.UserCredentialsRepository;
import com.example.BiddingBlitz.repository.user.UserAddressRepository;
import com.example.BiddingBlitz.repository.user.UserInfoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authorization.method.AuthorizeReturnObject;
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

}
