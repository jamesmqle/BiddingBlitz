package com.example.BiddingBlitz.model.authentication;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_credentials")
public class UserCredentials {

    @Id
    private Long userId;  // Primary key for UserCredentials. Also User ID associated with UserInfo, but without a foreign key constraint

    private String username;
    private String password;

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
}
