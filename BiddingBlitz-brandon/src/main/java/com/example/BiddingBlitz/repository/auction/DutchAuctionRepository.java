package com.example.BiddingBlitz.repository.auction;

import com.example.BiddingBlitz.model.auction.DutchAuction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DutchAuctionRepository extends JpaRepository<DutchAuction, Long> {
}
