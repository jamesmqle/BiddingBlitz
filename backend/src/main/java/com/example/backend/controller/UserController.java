package com.example.backend.controller;

import com.example.backend.dto.RegistrationRequest;
import com.example.backend.model.authentication.UserCredentials;
import com.example.backend.model.user.UserAddress;
import com.example.backend.model.user.UserInfo;
import com.example.backend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    }

    // Login GET Method
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("userCredentials", new UserCredentials());
        return "login";
    }

    // Login POST Method
    @PostMapping("/login")
    public String loginUser(@ModelAttribute UserCredentials userCredentials, HttpServletRequest request, Model model) {

        boolean isAuthenticated = userService.authenticateUser(userCredentials);  // Check if user credentials are valid

        if (isAuthenticated) {
        	HttpSession session = request.getSession();
        	Long sessionId = userCredentials.getUserId();
        	session.setAttribute("userId", sessionId);
        	System.out.println(session.getAttribute("userId"));

            return "redirect:/auction/welcome";
        } else {
            model.addAttribute("errorMessage", "Invalid username or password");
            return "login";
        }
    }
    

    // JSON STUFF BELOW

    //    @PostMapping("/register")
//    public ResponseEntity<String> registerUser(@RequestBody RegistrationRequest registrationDto) {
//        userService.registerUser(registrationDto.getUserCredentials(), registrationDto.getUserInfo(), registrationDto.getUserAddress());
//        return ResponseEntity.ok("Registration successful!");
//    }
//
//
//    @PostMapping("/login")
//    public ResponseEntity<String> loginUser(@RequestBody UserCredentials userCredentials, HttpServletRequest request) {
//        boolean isAuthenticated = userService.authenticateUser(userCredentials);  // Check if user credentials are valid
//
//        if (isAuthenticated) {
//            request.getSession().setAttribute("username", userCredentials.getUsername()); // store user details in session
//            return ResponseEntity.ok("Login successful");
//        } else {
//            return ResponseEntity.status(401).body("Invalid username or password");
//        }
//    }
}
