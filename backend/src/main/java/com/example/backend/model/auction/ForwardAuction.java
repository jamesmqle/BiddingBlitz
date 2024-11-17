package com.example.backend.model.auction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "forward_auction")
public class ForwardAuction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Primary key for ForwardAuction

    private Long itemId;// Foreign key

    private Double remainingTime;  // Remaining time in the auction

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
}
