package com.example.backend.dto;

public class TransactionDetailsDTO {

    private Long itemId;

    private Long winnerId;
    private Double totalPaid;
    private Integer shippingDays;

    public TransactionDetailsDTO() {};

    public TransactionDetailsDTO(Long itemId, Long winnerId, Double totalPaid, Integer shippingDays) {
        this.itemId = itemId;
        this.winnerId = winnerId;
        this.totalPaid = totalPaid;
        this.shippingDays = shippingDays;
    }

    public Integer getShippingDays() {
        return shippingDays;
    }

    public void setShippingDays(Integer shippingDays) {
        this.shippingDays = shippingDays;
    }

    public Double getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(Double totalPaid) {
        this.totalPaid = totalPaid;
    }

    public Long getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(Long winnerId) {
        this.winnerId = winnerId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }
}
