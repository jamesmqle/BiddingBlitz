package com.example.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import com.example.backend.service.*;
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
        return catalogueService.searchAuctions(keyword);
    }
}