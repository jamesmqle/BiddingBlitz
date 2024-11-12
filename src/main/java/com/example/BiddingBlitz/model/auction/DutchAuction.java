package com.example.BiddingBlitz.model.auction;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "dutch_auction")
public class DutchAuction {

    @Id
    private Long itemId;  // Primary key for DutchAuction

    private Double startingPrice;  // Starting price for the Dutch auction
    private Double currentPrice;   // Current price in the auction (decreases over time)
    private Double remainingTime;  // Remaining time for the auction
    private Long winnerId;         // ID of the winner (references a user without a foreign key constraint)

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
}
