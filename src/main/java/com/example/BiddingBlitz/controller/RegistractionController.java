package com.example.BiddingBlitz.controller;

import com.example.BiddingBlitz.model.authentication.UserCredentials;
import com.example.BiddingBlitz.model.user.UserAddress;
import com.example.BiddingBlitz.model.user.UserInfo;
import com.example.BiddingBlitz.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistractionController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userInfo", new UserInfo());
        model.addAttribute("userAddress", new UserAddress());
        model.addAttribute("userCredentials", new UserCredentials());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(
            @ModelAttribute("userInfo") UserInfo userInfo,
            @ModelAttribute("userAddress") UserAddress userAddress,
            @ModelAttribute("userCredentials") UserCredentials userCredentials,
            Model model) {

        userService.registerUser(userInfo, userAddress, userCredentials);
        model.addAttribute("successMessage", "User registered successfully!");
        return "register";
    }
}
