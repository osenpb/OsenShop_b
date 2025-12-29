package com.osen.ecommerce.core.product.repository;

import com.osen.ecommerce.core.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
