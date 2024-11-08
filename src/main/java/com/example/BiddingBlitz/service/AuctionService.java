package com.example.BiddingBlitz.service;

import com.example.BiddingBlitz.model.Item;
import com.example.BiddingBlitz.repository.ItemRepository;
import com.example.BiddingBlitz.repository.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuctionService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private BidRepository bidRepository;

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public void placeBid(Long itemId, Double bidAmount) {
        // Logic to place a bid and update the auction
    }
}
