package com.sity.service;

import com.sity.dto.request.ProductRequest;
import com.sity.dto.response.ProductResponse;
import com.sity.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);
    List<ProductResponse> findByName(String name);
    List<ProductResponse> findByPriceRange(Double minPrice, Double maxPrice);
    List<ProductResponse> findByCategoryId(Long categoryId);
}
