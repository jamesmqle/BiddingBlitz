package com.example.backend.service;

import com.example.backend.dto.TransactionDetailsDTO;
import com.example.backend.model.auction.Item;
import com.example.backend.model.payment.TransactionHistory;
import com.example.backend.repository.auction.ItemRepository;
import com.example.backend.repository.payment.TransactionHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private TransactionHistoryRepository transactionHistoryRepository;

    @Transactional
    public TransactionDetailsDTO processPayment(Long itemId, Long userId, boolean expeditedShipping) {
        Optional<Item> itemOpt = itemRepository.findById(itemId);
        if (itemOpt.isPresent()) {
            Item item = itemOpt.get();
            if (!item.getWinnerId().equals(userId)) {
                throw new IllegalArgumentException("Only the winning user can pay.");
            }

            double totalPaid = item.getItemPrice() + item.getShippingPrice();
            if (expeditedShipping) {
//                totalPaid += item.getIsExpeditedShipping();
            }

            Integer shippingDays = expeditedShipping ? 1 : 5;

            TransactionHistory transaction = new TransactionHistory();
            transaction.setItemId(itemId);
            transaction.setWinnerId(userId);
            transaction.setTotalPaid(totalPaid);
            transaction.setShippingDays(shippingDays);
            transactionHistoryRepository.save(transaction);

            TransactionDetailsDTO transactionDetails = new TransactionDetailsDTO(itemId, userId, totalPaid, shippingDays);

            return transactionDetails;
        }
        throw new IllegalArgumentException("Item not found.");
    }

    public TransactionDetailsDTO transactionInfo(Long itemId) {
        Optional<TransactionHistory> findTransaction = transactionHistoryRepository.findById(itemId);
        if (findTransaction.isPresent()) {
            TransactionHistory transaction = findTransaction.get();
            TransactionDetailsDTO transactionDetails = new TransactionDetailsDTO(itemId, transaction.getWinnerId(), transaction.getTotalPaid(), transaction.getShippingDays());
            return transactionDetails;
        }
        throw new IllegalArgumentException("Transaction does not exist");
    }
}
