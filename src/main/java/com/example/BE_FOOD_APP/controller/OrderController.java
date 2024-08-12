package com.example.BE_FOOD_APP.controller;

import com.example.BE_FOOD_APP.dto.OrderRequestDTO;
import com.example.BE_FOOD_APP.respo.respose.OrderResponse;
import com.example.BE_FOOD_APP.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final IOrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequestDTO orderRequest) {
        OrderResponse createdOrder = orderService.createOrder(orderRequest);
        return ResponseEntity.ok(createdOrder);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        List<OrderResponse> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long id) {
//        OrderResponse order = orderService.getOrderById(id);
//        if (order != null) {
//            return ResponseEntity.ok(order);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
   @GetMapping("/idUser")
    public ResponseEntity<List<OrderResponse>> getOrderByUserId(@RequestParam Long userId) {
        List<OrderResponse> orders = orderService.getOrderByUserId(userId);
        return ResponseEntity.ok(orders);
    }

}
