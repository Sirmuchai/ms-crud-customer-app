package com.sity.controller;

import com.sity.dto.request.ProductRequest;
import com.sity.dto.response.ProductResponse;
import com.sity.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest){
        ProductR\esponse productResponse = productService.
                createProduct(productRequest);
        return ResponseEntity.ok(productResponse);
    }


}
