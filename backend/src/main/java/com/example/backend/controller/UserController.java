package com.example.backend.controller;

import com.example.backend.model.authentication.UserCredentials;
import com.example.backend.model.user.UserAddress;
import com.example.backend.model.user.UserInfo;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userInfo", new UserInfo());
        model.addAttribute("userCredentials", new UserCredentials());
        model.addAttribute("userAddress", new UserAddress());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(
            @ModelAttribute UserInfo userInfo,
            @ModelAttribute UserCredentials userCredentials,
            @ModelAttribute UserAddress userAddress,
            Model model) {

        userService.registerUser(userCredentials, userInfo, userAddress);
        return "redirect:/login";
//        model.addAttribute("message", "Registration successful!");
//        return "registration_success";
    }

    // Login GET Method
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("userCredentials", new UserCredentials());
        return "login";
    }

    // Login POST Method
    @PostMapping("/login")
    public String loginUser(@ModelAttribute UserCredentials userCredentials, Model model) {

        boolean isAuthenticated = userService.authenticateUser(userCredentials);  // Check if user credentials are valid

        if (isAuthenticated) {
            return "welcome";
        } else {
            model.addAttribute("errorMessage", "Invalid username or password");
            return "login";
        }
    }
}
