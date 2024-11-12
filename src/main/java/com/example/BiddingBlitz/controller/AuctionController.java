package com.example.BiddingBlitz.controller;

import com.example.BiddingBlitz.service.AuctionService;
import com.example.BiddingBlitz.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuctionController {

//    @Autowired
//    private AuctionService auctionService;
//
//    @GetMapping("/catalog")
//    public String viewCatalog(Model model) {
//        model.addAttribute("items", auctionService.getAllItems());
//        return "catalog";
//    }
//
//    @GetMapping("/bid")
//    public String placeBid(Long itemId, Double bidAmount) {
//        auctionService.placeBid(itemId, bidAmount);
//        return "redirect:/catalog";
//    }
}
