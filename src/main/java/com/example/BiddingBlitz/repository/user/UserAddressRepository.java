package com.example.BiddingBlitz.repository.user;

import com.example.BiddingBlitz.model.user.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {
}
