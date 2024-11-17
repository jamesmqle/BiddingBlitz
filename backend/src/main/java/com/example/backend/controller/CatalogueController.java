package com.example.backend.controller;

import com.example.backend.dto.ItemDetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.backend.service.*;
import org.springframework.web.server.ResponseStatusException;

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

//    // GET request to fetch a single item by its ID and return as JSON
//    @GetMapping("/item/{id}")
//    @ResponseBody
//    public ItemDetailsDTO getItem(@PathVariable("id") Long id) {
//        // Fetch the item from the service by ID
//        Item item = catalogueService.getItemById(id);
//
//        if (item == null) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found");
//        }
//
//        // Map the Item to ItemDetailsDTO
//        ItemDetailsDTO itemDetailsDTO = new ItemDetailsDTO();
//        itemDetailsDTO.setItemId(item.getItemId());
//        itemDetailsDTO.setName(item.getName());
//        itemDetailsDTO.setDescription(item.getDescription());
//        itemDetailsDTO.setAuctionType(item.getAuctionType());
//        itemDetailsDTO.setItemPrice(item.getItemPrice());
//        itemDetailsDTO.setShippingPrice(item.getShippingPrice());
//        itemDetailsDTO.setIsExpeditedShipping(item.getIsExpeditedShipping());
//        itemDetailsDTO.setWinnerId(item.getWinnerId());
//
//        // Set auction-specific data based on auction type
//        if ("Forward".equalsIgnoreCase(item.getAuctionType())) {
//            ForwardAuction forwardAuction = catalogueService.getForwardAuctionByItemId(item.getItemId());
//            itemDetailsDTO.setRemainingTime(forwardAuction.getRemainingTime());
//        } else if ("Dutch".equalsIgnoreCase(item.getAuctionType())) {
//            DutchAuction dutchAuction = catalogueService.getDutchAuctionByItemId(item.getItemId());
//            itemDetailsDTO.setMinPrice(dutchAuction.getMinPrice());
//            itemDetailsDTO.setDecrementPrice(dutchAuction.getDecrementPrice());
//            itemDetailsDTO.setTimeInterval(dutchAuction.getTimeInterval());
//        }
//
//        return itemDetailsDTO;
//    }

//    // GET request to fetch search results as JSON
//    @GetMapping("/search")
//    @ResponseBody // Indicates JSON response
//    public List<Item> getSearchResults(@RequestParam("keyword") String keyword) {
//        List<Item> items = catalogueService.searchItemsAuctioned(keyword);
//        for (Item item : items) {
//            if ("Forward".equalsIgnoreCase(item.getAuctionType())) {
//                ForwardAuction forwardAuction = catalogueService.getForwardAuctionByItemId(item.getItemId());
//                item.setAuctionType("Forward Auction (Remaining Time: " + forwardAuction.getRemainingTime() + "hrs)");
//            } else if ("Dutch".equalsIgnoreCase(item.getAuctionType())) {
//                DutchAuction dutchAuction = catalogueService.getDutchAuctionByItemId(item.getItemId());
//                item.setAuctionType("Dutch Auction - Decrementing price of $" + dutchAuction.getDecrementPrice() + " per hour");
//            }
//        }
//        return items;
//    }

//    @PostMapping("/addItem")
//    public ResponseEntity<String> addItem(@RequestBody Item item) {
//        auctionService.addItem(item);
//        return ResponseEntity.ok("Item Successfully Registered");
//    }
}