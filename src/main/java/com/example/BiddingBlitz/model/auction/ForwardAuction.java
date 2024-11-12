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
@Table(name = "forward_auction")
public class ForwardAuction {

    @Id
    private Long itemId;  // item_id is the primary key and foreign key from the item table

    private Double remainingTime;  // Remaining time in the auction

    // Many-to-one relationship with Item (each Forward auction is linked to one item)
    @ManyToOne
    @JoinColumn(name = "item_id", insertable = false, updatable = false)
    private Item item;

    // Many-to-one relationship with UserInfo (each Forward auction has a winner from the UserInfo table)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "winner_id", foreignKey = @ForeignKey(name = "FK_forward_auction_user_info"))
    private UserInfo winner;  // The winner of the auction

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
