package com.example.backend.service;

import com.example.backend.model.auction.Item;
import com.example.backend.model.auction.ForwardAuction;
import com.example.backend.model.auction.DutchAuction;
import com.example.backend.repository.auction.ItemRepository;
import com.example.backend.repository.auction.ForwardAuctionRepository;
import com.example.backend.repository.auction.DutchAuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

@Service
public class AuctionService {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ForwardAuctionRepository forwardAuctionRepository;
    @Autowired
    private DutchAuctionRepository dutchAuctionRepository;

    // Starts the auction based on the type of auction (Forward or Dutch)
    public void startAuction(Item item) {
        if ("Forward".equalsIgnoreCase(item.getAuctionType())) {
            startForwardAuction(item);
        } else if ("Dutch".equalsIgnoreCase(item.getAuctionType())) {
            startDutchAuction(item);
        } else {
            throw new IllegalArgumentException("Unsupported auction type: " + item.getAuctionType());
        }
    }

    private void startForwardAuction(Item item) {
        // Logic for Forward Auction:
        item.setItemPrice(item.getItemPrice() != null ? item.getItemPrice() : 0.0);
        Timer timer = new Timer();

        // For example, 10-minute duration for forward auction
        long auctionDuration = 10 * 60 * 1000; // 10 minutes in milliseconds
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // End auction and notify users
                endAuction(item.getItemId());
            }
        }, auctionDuration);

        System.out.println("Forward auction started for item: " + item.getName());
    }

    private void startDutchAuction(Item item) {
        // Initial decrement price and frequency (e.g., decrement every 2 minutes)
        double decrementAmount = item.getItemPrice() * 0.05; // 5% decrement
        long decrementInterval = 2 * 60 * 1000; // 2 minutes in milliseconds
        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (item.getItemPrice() > decrementAmount) {
                    item.setItemPrice(item.getItemPrice() - decrementAmount);
                    System.out.println("Dutch auction price updated for item: " + item.getName() +
                            " - New price: $" + item.getItemPrice());
                } else {
                    timer.cancel();
                    endAuction(item.getItemId());
                }
            }
        }, 0, decrementInterval);

        System.out.println("Dutch auction started for item: " + item.getName());
    }

    public void placeBid(Long itemId, Double bidAmount, Long userId) throws Exception {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new Exception("Item not found"));

        if ("Forward".equalsIgnoreCase(item.getAuctionType())) {
            ForwardAuction auction = forwardAuctionRepository.findById(itemId)
                    .orElseThrow(() -> new Exception("Auction not found"));

            if (bidAmount > item.getItemPrice()) {
                item.setItemPrice(bidAmount);
                item.setWinnerId(userId);
                itemRepository.save(item);
            } else {
                throw new Exception("Bid amount must be higher than current price.");
            }
        } else if ("Dutch".equalsIgnoreCase(item.getAuctionType())) {
            DutchAuction auction = dutchAuctionRepository.findById(itemId)
                    .orElseThrow(() -> new Exception("Auction not found"));
            item.setItemPrice(bidAmount);
            item.setWinnerId(userId);
            itemRepository.save(item);

        } else {
            throw new Exception("Item not up for auction.");
        }
    }

    public Item endAuction(Long itemId) {

        if (itemId == null) {
            throw new IllegalArgumentException("Item not found");
        }

        Item item = itemRepository.getReferenceById(itemId);
        item.setWinnerId(getWinningUserId(item));  // Assume logic to get winning user
        itemRepository.save(item);
        return item;
    }

    private Long getWinningUserId(Item item) {
        return item.getItemId()!=null ? item.getItemId() : null;
    }
    @Transactional
    public void itemInfo(Long itemId, Item item) {
    	Optional<Item> findItem = itemRepository.findById(itemId);
    	Item originalItem = findItem.get();
    	item.setAuctionType(originalItem.getAuctionType());
    	item.setExpeditedShipping(originalItem.getExpeditedShipping());
    	item.setItemId(originalItem.getItemId());
    	item.setItemPrice(originalItem.getItemPrice());
    	item.setName(originalItem.getName());
    	item.setShippingPrice(originalItem.getShippingPrice());
    	item.setWinnerId(originalItem.getWinnerId());
    	
    }
    
}
