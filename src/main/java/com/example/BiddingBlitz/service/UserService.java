package com.example.BiddingBlitz.service;

import com.example.BiddingBlitz.model.authentication.UserCredentials;
import com.example.BiddingBlitz.model.user.UserAddress;
import com.example.BiddingBlitz.model.user.UserInfo;
import com.example.BiddingBlitz.repository.authentication.UserCredentialsRepository;
import com.example.BiddingBlitz.repository.user.UserAddressRepository;
import com.example.BiddingBlitz.repository.user.UserInfoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authorization.method.AuthorizeReturnObject;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private UserAddressRepository userAddressRepository;

    @Autowired
    UserCredentialsRepository userCredentialsRepository;

    @Transactional
    public void registerUser(UserInfo userInfo, UserAddress address, UserCredentials credentials){
        userInfoRepository.save(userInfo);
        userAddressRepository.save(address);
        userCredentialsRepository.save(credentials);
    }
}
