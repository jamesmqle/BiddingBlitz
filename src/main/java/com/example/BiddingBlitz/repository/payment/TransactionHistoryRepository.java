package com.example.BiddingBlitz.repository.payment;

import com.example.BiddingBlitz.model.payment.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {
}
