package com.example.BiddingBlitz.repository.authentication;

import com.example.BiddingBlitz.model.authentication.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCredentialsRepository extends JpaRepository<UserCredentials, Long> {
    UserCredentials findByUsername(String username);
}
