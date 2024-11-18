package com.example.backend.model.auction;

import jakarta.persistence.*;

@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;  // item_id is the primary key

    private String name;
    private String description;
    private String auctionType;  // The type of auction (e.g., "Forward", "Dutch")
    private String auctionStatus; // status of auction (e.g., "Active", "Ended")
    private Double itemPrice;    // Price of the item
    private Double shippingPrice;  // Shipping price for the item
    private Boolean isExpeditedShipping;  // Whether user chooses expedited shipping
    private Long winnerId;         // ID of the winner (references a user without a foreign key constraint)

    // Getters and setters
    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getAuctionType() {
        return auctionType;
    }

    public void setAuctionType(String auctionType) {
        this.auctionType = auctionType;
    }

    public String getAuctionStatus() {
        return auctionStatus;
    }

    public void setAuctionStatus(String auctionStatus) {
        this.auctionStatus = auctionStatus;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Double getShippingPrice() {
        return shippingPrice;
    }

    public void setShippingPrice(Double shippingPrice) {
        this.shippingPrice = shippingPrice;
    }

    public Boolean getIsExpeditedShipping() {
        return isExpeditedShipping;
    }

    public void setIsExpeditedShipping(Boolean isExpeditedShipping) {
        this.isExpeditedShipping = isExpeditedShipping;
    }

    public Long getWinnerId() {
        return this.winnerId;
    }

    public void setWinnerId(Long winnerId) {
        this.winnerId = winnerId;
    }
}
