package com.example.backend.dto;

import com.example.backend.model.auction.DutchAuction;
import com.example.backend.model.auction.ForwardAuction;
import com.example.backend.model.auction.Item;

public class ItemDetailsDTO {
    private Item item;
    private ForwardAuction forwardAuction;
    private DutchAuction dutchAuction;

    public ItemDetailsDTO (Item item) {
        this.item = item;
    }

    public ItemDetailsDTO(Item item, ForwardAuction forwardAuction) {
        this.item = item;
        this.forwardAuction = forwardAuction;
    }

    public ItemDetailsDTO(Item item, DutchAuction dutchAuction) {
        this.item = item;
        this.dutchAuction = dutchAuction;
    }
    // Getters and setters

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public ForwardAuction getForwardAuction() {
        if ("Forward".equalsIgnoreCase(item.getAuctionType())) {
            return forwardAuction;
        }
        return null;  // Return null if it's not a Forward Auction
    }

    public void setForwardAuction(ForwardAuction forwardAuction) {
        this.forwardAuction = forwardAuction;
    }

    public DutchAuction getDutchAuction() {
        if ("Dutch".equalsIgnoreCase(item.getAuctionType())) {
            return dutchAuction;
        }
        return null;  // Return null if it's not a Dutch Auction
    }

    public void setDutchAuction(DutchAuction dutchAuction) {
        this.dutchAuction = dutchAuction;
    }
}
