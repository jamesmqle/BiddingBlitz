package com.example.backend.controller;

import com.example.backend.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auction")
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
    @PostMapping("/end/{itemId}")
    public ResponseEntity<String> endAuction(@PathVariable Long itemId) {
        auctionService.endAuction(itemId);
        return ResponseEntity.ok("Auction ended successfully.");
    }
}