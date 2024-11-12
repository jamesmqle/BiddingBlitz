package com.example.BiddingBlitz.model.auction;

import jakarta.persistence.*;

@Entity
@Table(name = "dutch_auction")
public class DutchAuction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;  // Primary key for DutchAuction, also a foreign key

    private Double startingPrice;  // Starting price for the Dutch auction
    private Double currentPrice;   // Current price in the auction (decreases over time)
    private Double remainingTime;  // Remaining time for the auction
    private Long winnerId;         // ID of the winner (references a user without a foreign key constraint)

    // One-to-one relationship with Item (itemId is used as the foreign key)
    @OneToOne
    @JoinColumn(name = "itemId", referencedColumnName = "itemId", insertable = false, updatable = false)
    private Item item;

    // Getters and setters
    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Double getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(Double startingPrice) {
        this.startingPrice = startingPrice;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Double getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(Double remainingTime) {
        this.remainingTime = remainingTime;
    }

    public Long getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(Long winnerId) {
        this.winnerId = winnerId;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
