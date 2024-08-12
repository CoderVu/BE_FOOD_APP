package com.example.BE_FOOD_APP.service;

import com.example.BE_FOOD_APP.dto.OrderItemDTO;
import com.example.BE_FOOD_APP.dto.OrderRequestDTO;
import com.example.BE_FOOD_APP.respo.respose.OrderResponse;
import com.example.BE_FOOD_APP.respo.respose.OrderItemResponse;
import com.example.BE_FOOD_APP.model.Order;
import com.example.BE_FOOD_APP.model.OrderItem;
import com.example.BE_FOOD_APP.model.Product;
import com.example.BE_FOOD_APP.model.User;
import com.example.BE_FOOD_APP.respo.respository.OrderRepository;
import com.example.BE_FOOD_APP.respo.respository.ProductRepository;
import com.example.BE_FOOD_APP.respo.respository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    public OrderResponse createOrder(OrderRequestDTO orderRequest) {
        User user = userRepository.findById(orderRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now().toString());
        order.setStatus("PENDING");
        List<OrderItem> orderItems = new ArrayList<>();
        double totalAmount = 0.0;
        for (OrderItemDTO itemDTO : orderRequest.getOrderItems()) {
            Product product = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            OrderItem orderItem = new OrderItem();

            orderItem.setProduct(product);
            orderItem.setQuantity(itemDTO.getQuantity());
            orderItem.setPrice(product.getPrice() * itemDTO.getQuantity());
            orderItem.setImg(product.getImg());
            orderItem.setName(product.getName());
            orderItem.setOrder(order);

            orderItems.add(orderItem);
            totalAmount += orderItem.getPrice();
        }

        order.setOrderItems(orderItems);
        order.setTotalAmount(totalAmount);

        Order savedOrder = orderRepository.save(order);

        return toOrderResponse(savedOrder);
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(this::toOrderResponse).collect(Collectors.toList());
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        return toOrderResponse(order);
    }

    @Override
    public List<OrderResponse> getOrderByUserId(Long userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        return orders.stream().map(this::toOrderResponse).collect(Collectors.toList());
    }

    private OrderResponse toOrderResponse(Order order) {
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(order.getId());
        orderResponse.setUserId(order.getUser().getId());
        orderResponse.setStatus(order.getStatus());
        orderResponse.setOrderDate(order.getOrderDate());
        orderResponse.setTotalAmount(order.getTotalAmount());

        List<OrderItemResponse> orderItems = new ArrayList<>();
        for (OrderItem item : order.getOrderItems()) {
            OrderItemResponse orderItemResponse = new OrderItemResponse();
            orderItemResponse.setProductId(item.getProduct().getId());
            orderItemResponse.setQuantity(item.getQuantity());
            orderItemResponse.setPrice(item.getPrice());
            orderItemResponse.setName(item.getProduct().getName());
            orderItemResponse.setImg(item.getProduct().getImg());
            orderItems.add(orderItemResponse);
        }

        orderResponse.setOrderItems(orderItems);

        return orderResponse;
    }
}