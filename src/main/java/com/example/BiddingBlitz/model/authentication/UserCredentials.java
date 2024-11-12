package com.example.BiddingBlitz.model.authentication;

import com.example.BiddingBlitz.model.user.UserInfo;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_credentials")
public class UserCredentials {

    @Id
    private Long userId;  // user_id is the primary key and foreign key from user_info

    private String username;
    private String password;

    // Many-to-one relationship with UserInfo (user_id references UserInfo entity)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_user_credentials_user_info"), insertable = false, updatable = false)
    private UserInfo userInfo;  // The associated user info

    // Getters and setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
