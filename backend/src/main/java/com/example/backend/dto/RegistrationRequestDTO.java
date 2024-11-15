package com.example.backend.dto;

import com.example.backend.model.authentication.UserCredentials;
import com.example.backend.model.user.UserAddress;
import com.example.backend.model.user.UserInfo;

public class RegistrationRequestDTO {
    private UserCredentials userCredentials;
    private UserInfo userInfo;
    private UserAddress userAddress;

    public UserCredentials getUserCredentials() { return userCredentials; }
    public void setUserCredentials(UserCredentials userCredentials) { this.userCredentials = userCredentials; }

    public UserInfo getUserInfo() { return userInfo; }
    public void setUserInfo(UserInfo userInfo) { this.userInfo = userInfo; }

    public UserAddress getUserAddress() { return userAddress; }
    public void setUserAddress(UserAddress userAddress) { this.userAddress = userAddress; }
}
