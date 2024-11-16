package com.example.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import com.example.backend.service.*;
import com.example.backend.model.auction.DutchAuction;
import com.example.backend.model.auction.ForwardAuction;
import com.example.backend.model.auction.Item;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CatalogueController {

    @Autowired
    private CatalogueService catalogueService;

    // GET request to serve the HTML page
    @GetMapping("/catalog/search")
    public String showSearchForm() {
        return "search"; // Serves search.html (mapped via Thymeleaf or static resource)
    }

    // GET request to fetch search results as JSON
    @GetMapping("/catalog/search/results")
    @ResponseBody // Indicates JSON response
    public List<Item> getSearchResults(@RequestParam("keyword") String keyword) {
    	List<Item> items = catalogueService.searchItemsAuctioned(keyword);
    	for (Item item : items) {
            if ("Forward".equalsIgnoreCase(item.getAuctionType())) {
                ForwardAuction forwardAuction = catalogueService.getForwardAuctionByItemId(item.getItemId());
                item.setAuctionType("Forward Auction (Remaining Time: " + forwardAuction.getRemainingTime() + "hrs)");
            } else if ("Dutch".equalsIgnoreCase(item.getAuctionType())) {
                DutchAuction dutchAuction = catalogueService.getDutchAuctionByItemId(item.getItemId());
                item.setAuctionType("Dutch Auction - Decrementing price of $" + dutchAuction.getDecrementPrice() + " per hour");
            }
        }
        return items;
    }
}