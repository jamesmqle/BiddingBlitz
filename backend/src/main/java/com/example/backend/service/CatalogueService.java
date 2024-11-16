// CatalogueService.java
package com.example.backend.service;

import com.example.backend.model.auction.DutchAuction;
import com.example.backend.model.auction.ForwardAuction;
import com.example.backend.model.auction.Item;
import com.example.backend.repository.auction.DutchAuctionRepository;
import com.example.backend.repository.auction.ForwardAuctionRepository;
import com.example.backend.repository.auction.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogueService {

    private final ItemRepository itemRepository;
    private final DutchAuctionRepository dutchAuctionRepository;
    private final ForwardAuctionRepository forwardAuctionRepository;

    @Autowired
    public CatalogueService(ItemRepository itemRepository, DutchAuctionRepository dutchAuctionRepository, ForwardAuctionRepository forwardAuctionRepository) {
        this.itemRepository = itemRepository;
        this.dutchAuctionRepository = dutchAuctionRepository;
        this.forwardAuctionRepository = forwardAuctionRepository;
    }

    public List<Item> searchItemsAuctioned(String keyword) {
        // Use the repository method with wildcard matching
        return itemRepository.findByNameContaining(keyword);
    }
    
    public ForwardAuction getForwardAuctionByItemId(Long itemId) {
        // Fetch the forward auction details using the itemId
        return forwardAuctionRepository.findByItemId(itemId);
    }

    public DutchAuction getDutchAuctionByItemId(Long itemId) {
        // Fetch the Dutch auction details using the itemId
        return dutchAuctionRepository.findByItemId(itemId);
    }
}
