package com.example.BiddingBlitz.repository.auction;

import com.example.BiddingBlitz.model.auction.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
