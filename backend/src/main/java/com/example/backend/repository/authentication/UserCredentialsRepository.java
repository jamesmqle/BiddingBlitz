package com.example.backend.repository.authentication;

import com.example.backend.model.authentication.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCredentialsRepository extends JpaRepository<UserCredentials, Long> {
}
