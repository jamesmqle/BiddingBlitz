package com.example.backend.repository.auction;

import com.example.backend.model.auction.DutchAuction;
import com.example.backend.model.auction.ForwardAuction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForwardAuctionRepository extends JpaRepository<ForwardAuction, Long> {
    ForwardAuction findByItemId(Long itemId);
}
