package com.example.backend.repository.auction;

import com.example.backend.model.auction.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
