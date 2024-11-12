package com.example.BiddingBlitz.model.auction;

import com.example.BiddingBlitz.model.user.UserInfo;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "dutch_auction")
public class DutchAuction {

    @Id
    private Long itemId;  // item_id is the primary key and foreign key from the item table

    private Double startingPrice;  // Starting price for the Dutch auction
    private Double currentPrice;   // Current price in the auction (this price will decrease over time)
    private Double remainingTime;  // Remaining time for the auction
    private Long winnerId;         // ID of the winner (can be mapped to UserInfo)

    // Many-to-one relationship with Item (each Dutch auction is linked to one item)
    @ManyToOne
    @JoinColumn(name = "item_id", insertable = false, updatable = false)
    private Item item;

    // Many-to-one relationship with UserInfo (each Dutch auction has a winner from the UserInfo table)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "winner_id", foreignKey = @ForeignKey(name = "FK_dutch_auction_user_info"))
    private UserInfo winner;  // The winner of the auction

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

    public UserInfo getWinner() {
        return winner;
    }

    public void setWinner(UserInfo winner) {
        this.winner = winner;
    }
}
