package com.example.BE_FOOD_APP.respo.respose;

import java.util.List;

public class OrderResponse {
    private Long id;
    private Long userId;
    private String orderDate;
    private Double totalAmount;
    private String status;
    private List<OrderItemResponse> orderItems;


    // Getters and setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setOrderItems(List<OrderItemResponse> orderItems) {
        this.orderItems = orderItems;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getOrderDate() {
        return orderDate;
    }
    public String getStatus() {
        return status;
    }
    public Double getTotalAmount() {
        return totalAmount;
    }

    public List<OrderItemResponse> getOrderItems() {
        return orderItems;
    }

}
