package com.example.backend.factory;

import com.example.backend.model.auction.DutchAuction;
import com.example.backend.model.auction.Item;
import com.example.backend.repository.auction.DutchAuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("DutchAuctionFactory")
public class DutchAuctionFactory implements AuctionFactory {

    @Autowired
    private DutchAuctionRepository dutchAuctionRepository;

    @Override
    public void startAuction(Item item) {
        double decrementAmount = item.getItemPrice() * 0.05; // 5% decrement
        long decrementInterval = 2 * 60 * 1000; // 2 minutes in milliseconds
        double minPrice = item.getItemPrice() * 0.5; // 50% min price

        DutchAuction dutchAuction = new DutchAuction();
        dutchAuction.setItem(item);
        dutchAuction.setItemId(item.getItemId());
        dutchAuction.setDecrementPrice(decrementAmount);
        dutchAuction.setTimeInterval((double) decrementInterval);
        dutchAuction.setMinPrice(minPrice);

        dutchAuctionRepository.save(dutchAuction);
        System.out.println("Dutch auction started for item: " + item.getName());
    }


}
