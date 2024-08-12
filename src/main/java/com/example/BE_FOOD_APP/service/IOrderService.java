package com.example.BE_FOOD_APP.service;

import com.example.BE_FOOD_APP.dto.OrderRequestDTO;
import com.example.BE_FOOD_APP.respo.respose.OrderResponse;
import com.example.BE_FOOD_APP.model.Order;

import java.util.List;

public interface IOrderService {


    OrderResponse createOrder(OrderRequestDTO orderRequest);

    List<OrderResponse> getAllOrders();

    OrderResponse getOrderById(Long id);

    List<OrderResponse> getOrderByUserId(Long userId);
}
