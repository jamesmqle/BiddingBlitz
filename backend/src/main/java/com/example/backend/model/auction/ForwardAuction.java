package com.example.backend.model.auction;

import jakarta.persistence.*;

@Entity
@Table(name = "forward_auction")
public class ForwardAuction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Primary key for ForwardAuction, also a foreign key
    
    private Long itemId;

    private Double remainingTime;  // Remaining time in the auction

    // One-to-one relationship with Item (itemId is used as the foreign key)
    @OneToOne
    @JoinColumn(name = "itemId", referencedColumnName = "itemId", insertable = false, updatable = false)
    private Item item;

    // Getters and setters


    public Double getRemainingTime() {
        return remainingTime;
    }

    public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setRemainingTime(Double remainingTime) {
        this.remainingTime = remainingTime;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
