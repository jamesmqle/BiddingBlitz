package com.example.BiddingBlitz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import com.example.BiddingBlitz.service.*;
import com.example.BiddingBlitz.model.auction.Item;

@Controller
public class CatalogueController {

    @Autowired
    private CatalogueService catalogueService;

    // GET request to display the search form
    @GetMapping("/catalog/search")
    public String showSearchForm() {
        return "search";
    }

    // POST request to handle the search submission
    @PostMapping("/catalog/search")
    public String handleSearch(@RequestParam("keyword") String keyword, Model model) {
        List<Item> results = catalogueService.searchAuctions(keyword);

        // Add search results to the model
        model.addAttribute("results", results);

        return "searchResults";
    }
}
