// CatalogueService.java
package com.example.backend.service;

import com.example.backend.model.auction.Item;
import com.example.backend.repository.auction.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogueService {

    private final ItemRepository itemRepository;

    @Autowired
    public CatalogueService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> searchAuctions(String keyword) {
        // Use the repository method with wildcard matching
        return itemRepository.findByNameContaining(keyword);
    }
}
