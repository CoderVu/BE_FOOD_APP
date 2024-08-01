package com.example.BE_FOOD_APP.controller;

import com.example.BE_FOOD_APP.dto.ProductResponse;
import com.example.BE_FOOD_APP.model.Product;
import com.example.BE_FOOD_APP.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllProducts() throws SQLException {
        return ResponseEntity.ok(productService.getAllProduct());
    }

    @GetMapping("/popular")
    public ResponseEntity<ProductResponse> getPopularProducts() {
        ProductResponse productResponse = productService.getPopularProductResponse();
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }
    @GetMapping("/recommended")
    public ResponseEntity<ProductResponse> getRecommendProducts() {
        ProductResponse productResponse = productService.getRecommendProductResponse();
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }


}
