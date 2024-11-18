package com.example.backend.service;

import com.example.backend.model.auction.Item;
import com.example.backend.model.user.UserAddress;
import com.example.backend.model.user.UserInfo;
import com.example.backend.model.auction.ForwardAuction;
import com.example.backend.dto.UserDetailsDTO;
import com.example.backend.model.auction.DutchAuction;
import com.example.backend.repository.auction.ItemRepository;
import com.example.backend.repository.user.UserInfoRepository;
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
    @Autowired
    private UserInfoRepository userInfoRepository;
    

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
            ForwardAuction auction = forwardAuctionRepository.findByItemId(itemId);

            if (bidAmount > item.getItemPrice()) {
                item.setItemPrice(bidAmount);
                item.setWinnerId(userId);
                item.addBidderId(userId);
                itemRepository.save(item);
            } else {
                throw new Exception("Bid amount must be higher than current price.");
            }
        } else if ("Dutch".equalsIgnoreCase(item.getAuctionType())) {
            DutchAuction auction = dutchAuctionRepository.findByItemId(itemId);
            item.setItemPrice(bidAmount);
            item.setWinnerId(userId);
            item.addBidderId(userId);
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
    public List<UserDetailsDTO> endAuction(Long itemId) throws Exception {
        Item item = itemRepository.findByItemId(itemId)
                .orElseThrow(() -> new Exception("Item not found"));

        if (!"Active".equalsIgnoreCase(item.getAuctionStatus())) {
            throw new Exception("Auction has already ended or item not active for auction.");
        }

        item.setAuctionStatus("Ended");
        itemRepository.save(item);

        // Notify users and collect bidder details
        List<Long> bidderIds = item.getBidderIds();
        List<UserDetailsDTO> bidderDetails = bidderIds.stream()
                .map(userId -> {
                    UserInfo userInfo = userInfoRepository.findById(userId)
                            .orElseThrow(() -> new RuntimeException("User not found"));

                    UserAddress address = userInfo.getAddress();
                    return new UserDetailsDTO(userInfo, address);
                })
                .toList();

        bidderIds.forEach(userId -> notifyUser(userId, item.getName(), item.getAuctionType()));

        return bidderDetails;
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
            if (auction != null) {
                auction.setRemainingTime(auction.getRemainingTime() - 6000);
                forwardAuctionRepository.save(auction);
                if (auction.getRemainingTime() <= 0) {
                    endAuction(auction.getItemId());
                }
            }
        }
    }
    
    @Transactional
    public List<UserDetailsDTO> getBiddersForItem(Long itemId) throws Exception {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new Exception("Item not found"));

        // Assuming you store user information in a User table
        List<Long> bidderIds = item.getBidderIds(); // Retrieve the list of bidder IDs

        return bidderIds.stream()
                .map(userId -> {
                    UserInfo userInfo = userInfoRepository.findById(userId)
                            .orElseThrow(() -> new RuntimeException("User not found"));

                    UserAddress address = userInfo.getAddress();
                    return new UserDetailsDTO(userInfo, address);
                })
                .toList();
    }

    
    private void notifyUser(Long userId, String itemName, String auctionType) {
        System.out.println("Notified user " + userId + " that the auction for " + itemName 
                + " (" + auctionType + ") has ended.");
    }
    
    
}

