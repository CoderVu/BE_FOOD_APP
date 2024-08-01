package com.example.BE_FOOD_APP.dto;

import com.example.BE_FOOD_APP.model.Product;

import java.util.List;

public class ProductResponse {
    private int totalSize;
    private int typeId;
    private int offset;
    private List<Product> products;

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
