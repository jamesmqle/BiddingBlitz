package com.example.backend.model.auction;

import jakarta.persistence.*;

@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;  // item_id is the primary key

    private String name;
    private String auctionType;  // The type of auction (e.g., "Forward", "Dutch")
    private Double itemPrice;    // Price of the item
    private Double shippingPrice;  // Shipping price for the item
    private Double expeditedShipping;  // Expedited shipping cost
    private Long winnerId;         // ID of the winner (references a user without a foreign key constraint)

    // Default constructor class
    public Item(){
        
    }

    // Constructor class
    public Item(Long itemId, String name, String auctionType, Double itemPrice, Double shippingPrice,
                Double expeditedShipping) {
        this.itemId = itemId;
        this.name = name;
        this.auctionType = auctionType;
        this.itemPrice = itemPrice;
        this.shippingPrice = shippingPrice;
        this.expeditedShipping = expeditedShipping;
    }

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

    public String getAuctionType() {
        return auctionType;
    }

    public void setAuctionType(String auctionType) {
        this.auctionType = auctionType;
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

    public Double getExpeditedShipping() {
        return expeditedShipping;
    }

    public void setExpeditedShipping(Double expeditedShipping) {
        this.expeditedShipping = expeditedShipping;
    }

    public Long getWinnerId() {
        return this.winnerId;
    }

    public void setWinnerId(Long winnerId) {
        this.winnerId = winnerId;
    }
}
