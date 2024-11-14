package com.example.backend.repository.auction;

import com.example.backend.model.auction.DutchAuction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DutchAuctionRepository extends JpaRepository<DutchAuction, Long> {
}
