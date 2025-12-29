package com.osen.ecommerce.core.product.controller;

import com.osen.ecommerce.core.product.dtos.ProductRequest;
import com.osen.ecommerce.core.product.dtos.ProductResponse;
import com.osen.ecommerce.core.product.mapper.ProductMapper;
import com.osen.ecommerce.core.product.model.Product;
import com.osen.ecommerce.core.product.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable Long id) {

        Product product = productService.findById(id);
        ProductResponse productResponse = ProductMapper.toDto(product);
        return ResponseEntity.ok(productResponse);
    }

    @PostMapping
    public ResponseEntity<?> saveProduct(@Valid ProductRequest productRequest) {
        Product newProduct = productService.save(productRequest);
        return ResponseEntity.accepted().body(newProduct);

    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProduct(){
        List<ProductResponse> productResponseList = productService.findAll().stream().map(ProductMapper::toDto).toList();
        return ResponseEntity.ok(productResponseList);
    }
}
