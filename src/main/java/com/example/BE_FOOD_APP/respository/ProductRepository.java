package com.example.BE_FOOD_APP.respository;

import com.example.BE_FOOD_APP.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {


    List<Product> findAllByIsPopular(boolean isPopular);

    List<Product> findAllByIsRecommended(boolean isRecommended);
}
