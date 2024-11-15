package com.example.backend.service;

import com.example.backend.exception.PaymentException;
import com.example.backend.model.auction.Item;
import com.example.backend.model.payment.TransactionHistory;
import com.example.backend.model.user.UserInfo;
import com.example.backend.repository.auction.ItemRepository;
import com.example.backend.repository.payment.TransactionHistoryRepository;
import com.example.backend.repository.user.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private TransactionHistoryRepository transactionHistoryRepository;

    public TransactionHistory processPayment(Long itemId, Long userId, String creditCardNumber,
                                             String cardholderName, String expirationDate, String securityCode) throws PaymentException {
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new PaymentException("Invalid auction item"));
        UserInfo user = userInfoRepository.findById(userId).orElseThrow(() -> new PaymentException("Invalid user"));

        // Mock payment validation (In real-life scenarios, integrate with a payment gateway)
        if (!validatePaymentDetails(creditCardNumber, expirationDate, securityCode)) {
            throw new PaymentException("Invalid payment details");
        }

        // Calculate total payment (item price + shipping cost)
        double totalAmount = item.getItemPrice() + item.getShippingPrice();

        // Create a transaction record
        TransactionHistory transaction = new TransactionHistory();
        transaction.setItemId(item.getItemId());
        transaction.setShippingDays(7); // random shipping day
        transaction.setTotalPaid(totalAmount);
        transaction.setWinnerId(user.getUserId());

        // Save transaction in the database
        return transactionHistoryRepository.save(transaction);
    }

    // Payment details validation (for example purposes, assuming simple checks)
    private boolean validatePaymentDetails(String creditCardNumber, String expirationDate, String securityCode) {
        // Mock validation logic, can be expanded
        return creditCardNumber.length() == 16 && expirationDate.matches("^(0[1-9]|1[0-2])\\/([0-9]{2})$") && securityCode.length() == 3;
    }

}
