package com.example.BiddingBlitz.repository;

import com.example.BiddingBlitz.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
