package com.example.backend.controller;

import com.example.backend.dto.ItemDetailsDTO;
import com.example.backend.dto.UserDetailsDTO;
import com.example.backend.service.AuctionService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auction")
public class AuctionController {

    @Autowired
    private AuctionService auctionService;

    // For bidding in a forward auction
    @PostMapping("/bid/{itemId}")
    public ResponseEntity<String> placeBid(
            @PathVariable Long itemId,
            @RequestParam Double bidAmount,
            @RequestParam Long userId) {

        try {
            auctionService.placeBid(itemId, bidAmount, userId);
            return ResponseEntity.ok("Bid placed successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // To handle auction end event for both types
    @GetMapping("/end/{itemId}")
    public List<UserDetailsDTO> endAuction(@PathVariable Long itemId) throws Exception {
        return auctionService.endAuction(itemId);
    }

    // Command to Register Items
    @PostMapping("/addItem")
    public ResponseEntity<String> registerItem(@RequestBody ItemDetailsDTO item) {
        auctionService.addItem(item.getItem());
        return ResponseEntity.ok("Item Successfully Registered");
    }
}
