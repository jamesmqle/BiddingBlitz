package com.example.backend.model.auction;

import jakarta.persistence.*;

@Entity
@Table(name = "dutch_auction")
public class DutchAuction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;  // Primary key for DutchAuction, also a foreign key

    private Double decrementPrice;   // amount the price decreases over time
    private Double time;  // Remaining time for the auction

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

    public Double getDecrementPrice() {
        return decrementPrice;
    }

    public void setDecrementPrice(Double decrementPrice) {
        this.decrementPrice = decrementPrice;
    }

    public Double getRemainingTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
