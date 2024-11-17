package com.example.backend.service;

import com.example.backend.model.auction.Item;
import com.example.backend.model.auction.ForwardAuction;
import com.example.backend.model.auction.DutchAuction;
import com.example.backend.repository.auction.ItemRepository;
import com.example.backend.repository.auction.ForwardAuctionRepository;
import com.example.backend.repository.auction.DutchAuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        // Forward Auction setup
        item.setItemPrice(item.getItemPrice() != null ? item.getItemPrice() : 0.0);
        long auctionDuration = 10 * 60 * 1000; // 10 minutes in milliseconds

        ForwardAuction forwardAuction = new ForwardAuction();
        forwardAuction.setItem(item);
        forwardAuction.setItemId(item.getItemId());
        forwardAuction.setRemainingTime((double) auctionDuration);

        forwardAuctionRepository.save(forwardAuction);
        System.out.println("Forward auction started for item: " + item.getName());
    }

    @Transactional
    private void startDutchAuction(Item item) {
        // Dutch Auction setup

        double decrementAmount = item.getItemPrice() * 0.05; // 5% decrement
        long decrementInterval = 2 * 60 * 1000; // 2 minutes in milliseconds
        double minPrice = item.getItemPrice() * 0.5; // 50% min price

        DutchAuction dutchAuction = new DutchAuction();
        dutchAuction.setItem(item);
        dutchAuction.setItemId(item.getItemId());
        dutchAuction.setDecrementPrice(decrementAmount);
        dutchAuction.setTimeInterval((double)decrementInterval);
        dutchAuction.setMinPrice(minPrice);

        dutchAuctionRepository.save(dutchAuction);
        System.out.println("Dutch auction started for item: " + item.getName());
    }

    @Transactional
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
            endAuction(itemId);
        } else {
            throw new Exception("Item not up for auction.");
        }
    }

    @Transactional
    public void addItem(Item item) {
        Item savedItem = itemRepository.save(item);
        startAuction(savedItem);
    }

    @Transactional
    public void endAuction(Long itemId) throws Exception {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new Exception("item not found"));


        if (item != null) {
            if ("Active".equalsIgnoreCase(item.getAuctionStatus())) {
                item.setAuctionStatus("Ended");
                itemRepository.save(item);
            }
            else {
                throw new Exception("Auction has already ended");
            }
        }
        else {
            throw new Exception("item not found");
        }
    }

    private Long getWinningUserId(Item item) {
        return item.getItemId()!=null ? item.getItemId() : null;
    }

    //
    @Transactional
    public void updatePrice(Long itemId) {
        Item item = itemRepository.getReferenceById(itemId);
        if ("Dutch".equalsIgnoreCase(item.getAuctionType())) {
            DutchAuction auction = dutchAuctionRepository.getReferenceById(itemId);
            if (item.getItemPrice() > auction.getMinPrice()) {
                Double newPrice = item.getItemPrice() - auction.getDecrementPrice();
                item.setItemPrice(newPrice);
                itemRepository.save(item);
            }
        }
    }

    @Transactional
    @Scheduled(initialDelay = 12000, fixedDelay = 12000)
    public void dutchPriceSystem() {
        List<DutchAuction> auctionList = dutchAuctionRepository.findByItemWinnerIdIsNull();
        for (DutchAuction auction : auctionList) {
            if (auction != null) {
                Item item = auction.getItem();
                if (item.getItemPrice() > auction.getMinPrice()) {
                    Double newPrice = item.getItemPrice() - auction.getDecrementPrice();
                    item.setItemPrice(newPrice);
                    itemRepository.save(item);
                }
            }
        }
    }

    @Transactional
    @Scheduled(initialDelay = 6000, fixedDelay = 6000)
    public void forwardTimeSystem() throws Exception {
        List<ForwardAuction> auctionList = forwardAuctionRepository.findByRemainingTimeGreaterThan(0L);
        for (ForwardAuction auction : auctionList) {
            System.out.println(auction);
            if (auction != null) {
                if (auction.getRemainingTime() > 0)
                auction.setRemainingTime(auction.getRemainingTime() - 6000);
                forwardAuctionRepository.save(auction);
                if (auction.getRemainingTime() <= 0) {
                    endAuction(auction.getItemId());
                }
            }
        }
    }
}

