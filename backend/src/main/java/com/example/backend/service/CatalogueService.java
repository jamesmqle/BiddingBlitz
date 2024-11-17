package com.example.backend.service;

import com.example.backend.dto.ItemDetailsDTO;
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

    // Method to fetch Item and map it to ItemDetailsDTO
    public ItemDetailsDTO getItemDetails(Long itemId) {
        // Fetch Item by ID
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        // Check auction type and add relevant auction data to DTO
        if ("Forward".equalsIgnoreCase(item.getAuctionType())) {
            ForwardAuction forwardAuction = forwardAuctionRepository.findById(item.getItemId())
                    .orElseThrow(() -> new RuntimeException("Forward Auction data not found"));
            return new ItemDetailsDTO(item, forwardAuction);
        } else if ("Dutch".equalsIgnoreCase(item.getAuctionType())) {
            DutchAuction dutchAuction = dutchAuctionRepository.findById(item.getItemId())
                    .orElseThrow(() -> new RuntimeException("Dutch Auction data not found"));
            return new ItemDetailsDTO(item, dutchAuction);
        }

        return new ItemDetailsDTO(item);
    }
}