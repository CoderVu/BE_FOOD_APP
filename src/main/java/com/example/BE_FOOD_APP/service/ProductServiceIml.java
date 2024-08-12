package com.example.BE_FOOD_APP.service;

import com.example.BE_FOOD_APP.respo.respose.ProductResponse;
import com.example.BE_FOOD_APP.model.Product;

import java.util.List;

public interface ProductServiceIml {

    List<Product> getAllProduct();

    ProductResponse getPopularProductResponse();

    ProductResponse getRecommendProductResponse();
}
