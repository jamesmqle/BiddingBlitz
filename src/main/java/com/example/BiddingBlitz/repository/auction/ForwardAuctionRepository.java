package com.example.BiddingBlitz.repository.auction;

import com.example.BiddingBlitz.model.auction.ForwardAuction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForwardAuctionRepository extends JpaRepository<ForwardAuction, Long> {
}
