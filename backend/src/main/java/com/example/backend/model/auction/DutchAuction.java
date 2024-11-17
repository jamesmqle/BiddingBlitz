package com.example.backend.model.auction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "dutch_auction")
public class DutchAuction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Primary key for DutchAuction

    private Long itemId;// Foreign key
    private Double minPrice; // Minimum price the item can go for
    private Double decrementPrice;   // amount the price decreases over time
    private Double timeInterval;  // time interval (in seconds) price will decrease

    // One-to-one relationship with Item (itemId is used as the foreign key)
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "itemId", referencedColumnName = "itemId", insertable = false, updatable = false)
    private Item item;

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Double getMinPrice() { return minPrice; }

    public void setMinPrice(Double minPrice) { this.minPrice = minPrice; }

    public Double getDecrementPrice() {
        return decrementPrice;
    }

    public void setDecrementPrice(Double decrementPrice) {
        this.decrementPrice = decrementPrice;
    }

    public Double getTimeInterval() { return timeInterval; }

    public void setTimeInterval(Double timeInterval) {
        this.timeInterval = timeInterval;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
