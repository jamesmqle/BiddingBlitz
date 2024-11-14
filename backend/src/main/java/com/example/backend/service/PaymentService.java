package com.example.backend.service;

import com.example.backend.model.auction.Item;
import com.example.backend.model.payment.TransactionHistory;
import com.example.backend.repository.auction.ItemRepository;
import com.example.backend.repository.payment.TransactionHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private TransactionHistoryRepository transactionHistoryRepository;

    public TransactionHistory processPayment(Long itemId, Long userId, boolean expeditedShipping) {
        Optional<Item> itemOpt = itemRepository.findById(itemId);
        if (itemOpt.isPresent()) {
            Item item = itemOpt.get();
            if (!item.getWinnerId().equals(userId)) {
                throw new IllegalArgumentException("Only the winning user can pay.");
            }

            double totalPaid = item.getItemPrice() + item.getShippingPrice();
            if (expeditedShipping) {
                totalPaid += item.getExpeditedShipping();
            }

            TransactionHistory transaction = new TransactionHistory();
            transaction.setItemId(itemId);
            transaction.setWinnerId(userId);
            transaction.setTotalPaid(totalPaid);
            transaction.setShippingDays(expeditedShipping ? 1 : 5);
            transactionHistoryRepository.save(transaction);

            return transaction;
        }
        throw new IllegalArgumentException("Item not found.");
    }
}
