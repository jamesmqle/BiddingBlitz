package com.example.backend.repository.auction;

import com.example.backend.model.auction.DutchAuction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DutchAuctionRepository extends JpaRepository<DutchAuction, Long> {
    DutchAuction findByItemId(Long itemId);
    List<DutchAuction> findByItemWinnerIdIsNull();
}
