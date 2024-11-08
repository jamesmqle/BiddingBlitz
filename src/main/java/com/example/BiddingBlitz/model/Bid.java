package com.example.BiddingBlitz.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Bid {

    @Id
    private Long id;
    private Long itemId;
    private Long userId;
    private Double amount;

    // Getters and Setters
}
