package com.example.BE_FOOD_APP.dto;

import java.util.List;

public class OrderRequestDTO {
    private Long userId;
    private List<OrderItemDTO> orderItems;

    // Getters and setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public List<OrderItemDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDTO> orderItems) {
        this.orderItems = orderItems;
    }
}
