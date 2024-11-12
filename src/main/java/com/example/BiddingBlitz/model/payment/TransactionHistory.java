package com.example.BiddingBlitz.model.payment;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "transaction_history")
public class TransactionHistory {

    @Id
    private Long itemId;  // Primary key for TransactionHistory, without foreign key constraint

    private Long winnerId;       // ID of the winner (not a foreign key, avoids cross-database constraints)
    private Double totalPaid;    // Using Double for REAL type
    private Integer shippingDays;

    // Getters and setters
    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(Long winnerId) {
        this.winnerId = winnerId;
    }

    public Double getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(Double totalPaid) {
        this.totalPaid = totalPaid;
    }

    public Integer getShippingDays() {
        return shippingDays;
    }

    public void setShippingDays(Integer shippingDays) {
        this.shippingDays = shippingDays;
    }
}
