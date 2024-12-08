package com.example.backend.repository.auction;

import com.example.backend.model.auction.ForwardAuction;
import com.example.backend.model.auction.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
	Optional<Item> findByItemId(Long itemId);
    List<Item> findByNameContaining(String name);
    List<Item> findByAuctionStatusAndNameContaining(String auctionStatus,String name);
    List<Item> findByAuctionStatus(String name);
}
