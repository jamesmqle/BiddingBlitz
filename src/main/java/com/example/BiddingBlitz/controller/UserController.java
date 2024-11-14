package com.example.BiddingBlitz.controller;

import com.example.BiddingBlitz.model.authentication.UserCredentials;
import com.example.BiddingBlitz.model.user.UserAddress;
import com.example.BiddingBlitz.model.user.UserInfo;
import com.example.BiddingBlitz.service.UserService;
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
        model.addAttribute("message", "Registration successful!");
        return "registration_success";
    }
}
