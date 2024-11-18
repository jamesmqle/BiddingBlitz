package com.example.backend.dto;

import com.example.backend.model.user.UserAddress;
import com.example.backend.model.user.UserInfo;

public class UserDetailsDTO {

    private UserInfo userInfo;
    private UserAddress userAddress;

    // Constructor
    public UserDetailsDTO(UserInfo userInfo, UserAddress userAddress) {
        this.userInfo = userInfo;
        this.userAddress = userAddress;
    }

    // Getters and setters
    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public UserAddress getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(UserAddress userAddress) {
        this.userAddress = userAddress;
    }
}
