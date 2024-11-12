package com.example.BiddingBlitz.model.payment;

import com.example.BiddingBlitz.model.auction.Item;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "transaction_history")
public class TransactionHistory {

    @Id
    private Long itemId;  // Assuming item_id is the primary key

    private Long winnerId;
    private Double totalPaid;  // Using Double for REAL type
    private Integer shippingDays;

    // Assuming there's an Item entity and it has a relationship with TransactionHistory
    // You would need to adjust based on your Item class and how it relates to TransactionHistory
    @ManyToOne
    @JoinColumn(name = "item_id", insertable = false, updatable = false)
    private Item item;

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

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
