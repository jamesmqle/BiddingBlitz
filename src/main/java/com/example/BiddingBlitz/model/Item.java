package com.example.BiddingBlitz.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Item {

    @Id
    private Long id;
    private String name;
    private Double startingBid;

    // Getters and Setters
}
