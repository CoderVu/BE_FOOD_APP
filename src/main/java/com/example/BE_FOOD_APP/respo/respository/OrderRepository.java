package com.example.BE_FOOD_APP.respo.respository;

import com.example.BE_FOOD_APP.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUserId(Long userId);
}
