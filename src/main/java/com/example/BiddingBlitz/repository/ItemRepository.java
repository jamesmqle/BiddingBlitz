package com.example.BiddingBlitz.repository;

import com.example.BiddingBlitz.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
