package com.osen.ecommerce.core.product.controller;

import com.osen.ecommerce.core.category.model.Category;
import com.osen.ecommerce.core.category.service.CategoryService;
import com.osen.ecommerce.core.product.dtos.CreateProductRequest;
import com.osen.ecommerce.core.product.dtos.ProductResponse;
import com.osen.ecommerce.core.product.dtos.UpdateProductRequest;
import com.osen.ecommerce.core.product.mapper.ProductMapper;
import com.osen.ecommerce.core.product.model.Product;
import com.osen.ecommerce.core.product.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService){
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable Long id) {

        Product product = productService.findById(id);
        ProductResponse productResponse = ProductMapper.toDto(product);
        return ResponseEntity.ok(productResponse);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> saveProduct(@Valid CreateProductRequest productRequest) {
        return null;
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody @Valid UpdateProductRequest productRequest) throws EntityNotFoundException {
        Product updatedProducto = productService.update(id, productRequest);
        ProductResponse productResponse = ProductMapper.toDto(updatedProducto);
        return ResponseEntity.ok(productResponse);
    }

    //@PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProduct(){
        List<ProductResponse> productResponseList = productService.findAll().stream().map(ProductMapper::toDto).toList();
        return ResponseEntity.ok(productResponseList);
    }
}
