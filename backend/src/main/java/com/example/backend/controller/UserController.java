package com.example.backend.controller;

import com.example.backend.dto.RegistrationRequestDTO;
import com.example.backend.dto.UserDetailsDTO;
import com.example.backend.model.authentication.UserCredentials;
import com.example.backend.model.user.UserAddress;
import com.example.backend.model.user.UserInfo;
import com.example.backend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")  // Base URL for all user-related endpoints
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public UserDetailsDTO getUserDetails(@PathVariable Long userId) {
        return userService.getUserDetails(userId);
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userInfo", new UserInfo());
        model.addAttribute("userCredentials", new UserCredentials());
        model.addAttribute("userAddress", new UserAddress());
        return "register";
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegistrationRequestDTO registrationDto) {
        userService.registerUser(registrationDto.getUserCredentials(), registrationDto.getUserInfo(), registrationDto.getUserAddress());
        return ResponseEntity.ok("Registration successful!");
    }

    // Login GET Method
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("userCredentials", new UserCredentials());
        return "login";
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody UserCredentials userCredentials, HttpServletRequest request) {
        Long userId = userService.authenticateUser(userCredentials);  // Check if user credentials are valid and save userId if so

        if (userId != null) {

            // Manage Sessions with Spring Session Storage
            HttpSession session = request.getSession();
            session.setAttribute("userId", userId);
            System.out.println(session.getAttribute("userId"));

            // Return the userId to manage session in LocalStorage Client-side
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("userId", userId); // Send userId in the response

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body(Map.of("message", "Invalid username or password"));
        }
    }

//    // Login POST Method
//    @PostMapping("/login")
//    public String loginUser(@ModelAttribute UserCredentials userCredentials, Model model) {
//
//        boolean isAuthenticated = userService.authenticateUser(userCredentials);  // Check if user credentials are valid
//
//        if (isAuthenticated) {
//            return "welcome";
//        } else {
//            model.addAttribute("errorMessage", "Invalid username or password");
//            return "login";
//        }
//    }

    //    @PostMapping("/register")
//    public String registerUser(
//            @ModelAttribute UserInfo userInfo,
//            @ModelAttribute UserCredentials userCredentials,
//            @ModelAttribute UserAddress userAddress,
//            Model model) {
//
//        userService.registerUser(userCredentials, userInfo, userAddress);
//        return "redirect:/login";
//    }
}
