package com.example.backend.controller;

import com.example.backend.model.payment.TransactionHistory;
import com.example.backend.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/pay")
    public TransactionHistory payForAuction(@RequestParam Long itemId, @RequestParam Long userId, @RequestParam boolean expedited) {
        return paymentService.processPayment(itemId, userId, expedited);
    }

    @GetMapping("/paidInfo")
    public TransactionHistory paidInfo(@RequestParam Long itemId) {
        return paymentService.transactionInfo(itemId);
    }
}
