package com.example.backend.repository.auction;

import com.example.backend.model.auction.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByAuctionStatusAndNameContaining(String auctionStatus,String name);
    List<Item> findByAuctionStatus(String name);
}