package com.example.backend.controller;

import com.example.backend.dto.RegistrationRequestDTO;
import com.example.backend.dto.UserDetailsDTO;
import com.example.backend.model.authentication.UserCredentials;
import com.example.backend.service.UserServiceInterface;
import com.example.backend.service.UserServiceProxy;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserServiceInterface userService;

    @Autowired
    public UserController(UserServiceProxy userServiceProxy) {
        this.userService = userServiceProxy;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegistrationRequestDTO registrationDto) {
        userService.registerUser(registrationDto.getUserCredentials(), registrationDto.getUserInfo(), registrationDto.getUserAddress());
        return ResponseEntity.ok("Registration successful!");
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody UserCredentials userCredentials, HttpServletRequest request) {
        Long userId = userService.authenticateUser(userCredentials);

        if (userId != null) {
            HttpSession session = request.getSession();
            session.setAttribute("userId", userId);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("userId", userId);

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body(Map.of("message", "Invalid username or password"));
        }
    }

    @GetMapping("/{userId}")
    public UserDetailsDTO getUserDetails(@PathVariable Long userId) {
        return userService.getUserDetails(userId);
    }
}
