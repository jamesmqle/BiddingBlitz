package com.example.backend.controller;

import com.example.backend.exception.PaymentException;
import com.example.backend.model.auction.Item;
import com.example.backend.model.authentication.UserCredentials;
import com.example.backend.model.payment.TransactionHistory;
import com.example.backend.model.user.UserInfo;
import com.example.backend.repository.auction.ItemRepository;
import com.example.backend.repository.authentication.UserCredentialsRepository;
import com.example.backend.repository.user.UserInfoRepository;
import com.example.backend.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private UserCredentialsRepository userCredentialsRepository;

    @Autowired
    private ItemRepository itemRepository;

    // Show payment page with user address and auction details
    @GetMapping("/checkout/{auctionItemId}")
    public String showPaymentPage(@PathVariable Long auctionItemId, Model model, Principal principal) {
        UserCredentials username = userCredentialsRepository.findByUsername(principal.getName());
        Item item = itemRepository.findById(auctionItemId).orElseThrow(() -> new IllegalArgumentException("Invalid auction item"));

        model.addAttribute("user", username);
        model.addAttribute("auctionItem", item);
        return "payment/checkout";
    }

    // Handle payment form submission
    @PostMapping("/submit")
    public String submitPayment(@RequestParam Long auctionItemId, @RequestParam Long userId,
                                @RequestParam String creditCardNumber, @RequestParam String cardholderName,
                                @RequestParam String expirationDate, @RequestParam String securityCode,
                                Model model) throws PaymentException {
        TransactionHistory payment = paymentService.processPayment(auctionItemId, userId, creditCardNumber, cardholderName,
                expirationDate, securityCode);
        model.addAttribute("payment", payment);
        return "payment/success"; // Payment success page
    }
}
