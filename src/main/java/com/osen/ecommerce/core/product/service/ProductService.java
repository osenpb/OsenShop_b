package com.osen.ecommerce.core.product.service;

import com.osen.ecommerce.core.product.dtos.ProductRequest;
import com.osen.ecommerce.core.product.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findById(Long id);
    Product save(ProductRequest product);
    void deleteById(Long id);
    Product update(Product product);
    void updateFromRequest(Long id, ProductRequest productRequest);

}
