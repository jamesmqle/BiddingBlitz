package com.example.backend.factory;

import com.example.backend.model.auction.Item;

public interface AuctionFactory {
    void startAuction(Item item);
}
