package com.sity.service;


import com.sity.dto.request.ProductRequest;
import com.sity.dto.response.ProductResponse;
import com.sity.model.Category;
import com.sity.model.Product;
import com.sity.repository.CategoryRepository;
import com.sity.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    private ProductResponse convertToDto(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setName(product.getName());
        productResponse.setDescription(product.getDescription());
        productResponse.setPrice(product.getPrice());
        productResponse.setImageUrl(product.getImageUrl());
        productResponse.setCategoryId(product.getCategory().getId());
        return productResponse;
    }

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setImageUrl(productRequest.getImageUrl());
        Category category =
                categoryRepository.findById(productRequest.getCategoryId()).orElse(null);
        product.setCategory(category);
        product = productRepository.save(product);
        return convertToDto(product);

    }

    @Override
    public List<ProductResponse> findByName(String name) {
        List<Product> products =
                productRepository.findByNameContainingIgnoreCase(name);
        return products.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> findByPriceRange(Double minPrice, Double maxPrice) {
        return null;
    }

    @Override
    public List<ProductResponse> findByCategoryId(Long categoryId) {
        return null;
    }


}
