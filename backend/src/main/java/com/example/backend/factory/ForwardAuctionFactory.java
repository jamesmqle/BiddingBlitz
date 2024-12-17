package com.example.backend.factory;

import com.example.backend.model.auction.ForwardAuction;
import com.example.backend.model.auction.Item;
import com.example.backend.repository.auction.ForwardAuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("ForwardAuctionFactory")
public class ForwardAuctionFactory implements AuctionFactory {

    @Autowired
    private ForwardAuctionRepository forwardAuctionRepository;

    @Override
    public void startAuction(Item item) {
        item.setItemPrice(item.getItemPrice() != null ? item.getItemPrice() : 0.0);
        long auctionDuration = 10 * 60 * 1000; // 10 minutes in milliseconds

        ForwardAuction forwardAuction = new ForwardAuction();
        forwardAuction.setItem(item);
        forwardAuction.setItemId(item.getItemId());
        forwardAuction.setRemainingTime((double) auctionDuration);

        forwardAuctionRepository.save(forwardAuction);
        System.out.println("Forward auction started for item: " + item.getName());
    }
}
