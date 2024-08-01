package com.example.BE_FOOD_APP.service;

import com.example.BE_FOOD_APP.dto.ProductResponse;
import com.example.BE_FOOD_APP.model.Product;
import com.example.BE_FOOD_APP.respository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements ProductServiceIml {
    private final ProductRepository productRepository;

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }
    @Override
    public ProductResponse getPopularProductResponse() {
        List<Product> products = productRepository.findAllByIsPopular(true);
        ProductResponse productResponse = new ProductResponse();
        productResponse.setProducts(products);
        productResponse.setTotalSize(products.size());
        System.out.println("total size: " + products.size());
        productResponse.setOffset(0); // offset = 0
        productResponse.setTypeId(1); // typeId = 1
        return productResponse;
    }
    @Override
    public ProductResponse getRecommendProductResponse() {
        List<Product> products = productRepository.findAllByIsRecommended(true);
        ProductResponse productResponse = new ProductResponse();
        productResponse.setProducts(products);
        productResponse.setTotalSize(products.size());
        System.out.println("total size: " + products.size());
        productResponse.setOffset(0); // offset = 0
        productResponse.setTypeId(2); // typeId = 2
        return productResponse;
    }


}
