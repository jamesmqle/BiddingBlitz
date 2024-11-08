package com.example.BiddingBlitz.repository;

import com.example.BiddingBlitz.model.Bid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BidRepository extends JpaRepository<Bid, Long> {
}
