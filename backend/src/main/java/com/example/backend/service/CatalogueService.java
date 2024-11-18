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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    // Search for items based on the provided keyword, return all items if keyword is null
    public List<ItemDetailsDTO> searchItemsAuctioned(String keyword) {
        List<Item> items;

        if (keyword == null || keyword.isEmpty()) {
            // If keyword is null or empty, return all items
            items = itemRepository.findAll();
        } else {
            // If keyword is provided, perform a search by name containing the keyword
            items = itemRepository.findByNameContaining(keyword);
        }

        // Use the existing getItemDetails method to map each item to its respective DTO
        return items.stream()
                .map(item -> getItemDetails(item.getItemId()))  // Reuse the existing method for mapping
                .collect(Collectors.toList());
    }

    // Method to fetch Item and map it to ItemDetailsDTO
    public ItemDetailsDTO getItemDetails(Long itemId) {
        // Fetch Item by ID
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        // Check auction type and add relevant auction data to DTO
        if ("Forward".equalsIgnoreCase(item.getAuctionType())) {
            ForwardAuction forwardAuction = forwardAuctionRepository.findByItemId(item.getItemId());
            return new ItemDetailsDTO(item, forwardAuction);
        } else if ("Dutch".equalsIgnoreCase(item.getAuctionType())) {
            DutchAuction dutchAuction = dutchAuctionRepository.findByItemId(item.getItemId());
            return new ItemDetailsDTO(item, dutchAuction);
        }

        return new ItemDetailsDTO(item);
    }
}