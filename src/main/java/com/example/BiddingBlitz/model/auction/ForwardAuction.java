package com.example.BiddingBlitz.model.auction;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "forward_auction")
public class ForwardAuction {

    @Id
    private Long itemId;  // Primary key for ForwardAuction

    private Long winnerId;        // ID of the winner (not a foreign key, avoids cross-database constraints)
    private Double remainingTime;  // Remaining time in the auction

    // Getters and setters
    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
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
