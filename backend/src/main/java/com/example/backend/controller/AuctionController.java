package com.example.backend.controller;

import com.example.backend.service.AuctionService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import com.example.backend.model.auction.*;
import com.example.backend.model.authentication.UserCredentials;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
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
    
    @GetMapping("/bid/forward/{itemId}")
    public String forwardScreen(@PathVariable Long itemId, HttpServletRequest request, Model model) {
    	HttpSession session = request.getSession();
    	Long userId = (Long) session.getAttribute("userId");
    	
    	Item item = new Item();
    	
    	auctionService.itemInfo(itemId, item);
    	
    	if ("Forward".equalsIgnoreCase(item.getAuctionType())) {
    		model.addAttribute("itemName", item.getName());
        	model.addAttribute("shippingPrice", item.getShippingPrice());
        	model.addAttribute("itemPrice", item.getItemPrice());
        	model.addAttribute("highestBidder", item.getWinnerId());
        	model.addAttribute("userId", userId);
        	model.addAttribute("itemId", itemId);
        	
        	return "forwardScreen";
    	}
    	
    	return "welcome";
    	
    }
    
    @GetMapping("/bid/dutch/{itemId}")
    public String dutchScreen(@PathVariable Long itemId, HttpServletRequest request, Model model) {
    	HttpSession session = request.getSession();
    	Long userId = (Long) session.getAttribute("userId");
    	
    	Item item = new Item();
    	
    	auctionService.itemInfo(itemId, item);
    	
    	if ("Dutch".equalsIgnoreCase(item.getAuctionType())) {
    		model.addAttribute("itemName", item.getName());
        	model.addAttribute("shippingPrice", item.getShippingPrice());
        	model.addAttribute("bidAmount", item.getItemPrice());
        	model.addAttribute("itemId", itemId);
        	model.addAttribute("userId", userId);
        	
        	return "dutchScreen";
    	}
    	
    	return "welcome";
    }
    
    @GetMapping("/welcome")
    public String showTest(@ModelAttribute UserCredentials userCredentials, HttpServletRequest request, Model model) {
    	HttpSession session = request.getSession();
    	String checking = Long.toString((Long)session.getAttribute("userId"));
    	model.addAttribute("userId", checking);
        return "welcome";
    }
    
}
