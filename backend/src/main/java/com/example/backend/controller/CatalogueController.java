package com.example.backend.controller;

import com.example.backend.dto.ItemDetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.backend.service.*;

@RestController
@RequestMapping("/api/catalogue")  // Base URL for all user-related endpoints
public class CatalogueController {

    @Autowired
    private CatalogueService catalogueService;

//    @Autowired
//    private AuctionService auctionService;


    // GET request to fetch a single item by its ID and return as JSON
    @GetMapping("/item/{itemId}")
    @ResponseBody
    public ItemDetailsDTO getItemDetails(@PathVariable Long itemId) {
        return catalogueService.getItemDetails(itemId);
    }

    // GET request to fetch search results as JSON
    @GetMapping("/search")
    @ResponseBody // Indicates JSON response
    public List<ItemDetailsDTO> getSearchedItemDetails(@RequestParam(required = false) String keyword) {
        return catalogueService.searchItemsAuctioned(keyword);
    }

//    @PostMapping("/addItem")
//    public ResponseEntity<String> addItem(@RequestBody Item item) {
//        auctionService.addItem(item);
//        return ResponseEntity.ok("Item Successfully Registered");
//    }
}