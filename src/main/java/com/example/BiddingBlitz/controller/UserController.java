package com.example.BiddingBlitz.controller;

import com.example.BiddingBlitz.service.UserService;
import com.example.BiddingBlitz.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/signup")
    public String showSignupForm() {
        return "signup";
    }

    @PostMapping("/signup")
    public String handleSignup(User user) {
        userService.saveUser(user);
        return "redirect:/signin";
    }

    @GetMapping("/signin")
    public String showSigninForm() {
        return "signin";
    }

    @PostMapping("/signin")
    public String handleSignin(String username, String password, Model model) {
        if (userService.validateUser(username, password)) {
            return "redirect:/catalog";
        }
        model.addAttribute("error", "Invalid credentials");
        return "signin";
    }
}
