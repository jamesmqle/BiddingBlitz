package com.example.backend.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AuctionFactoryProvider {

    private final Map<String, AuctionFactory> auctionFactories;

    @Autowired
    public AuctionFactoryProvider(Map<String, AuctionFactory> auctionFactories) {
        this.auctionFactories = auctionFactories;
    }

    public AuctionFactory getAuctionFactory(String auctionType) {
        AuctionFactory factory = auctionFactories.get(auctionType + "AuctionFactory");
        if (factory == null) {
            throw new IllegalArgumentException("Unsupported auction type: " + auctionType);
        }
        return factory;
    }
}
