package com.example.BiddingBlitz.model.auction;

import jakarta.persistence.*;

@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itemId;  // item_id is the primary key

    private String name;
    private String auctionType;  // The type of auction (e.g., "Forward", "Dutch")
    private Double itemPrice;    // Price of the item
    private Double shippingPrice;  // Shipping price for the item
    private Double expeditedShipping;  // Expedited shipping cost

    // Getters and setters
    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
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
}
