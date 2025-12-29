package com.osen.ecommerce.core.product.dtos;

import com.osen.ecommerce.core.category.dto.CategoryResponse;

import java.time.LocalDate;

public record ProductResponseAdmin(
        Long id,
        String name,
        String description,
        Double price,
        Integer stock,
        String imageUrl,
        CategoryResponse category,
        Boolean isActive,
        LocalDate createdAt
) {

}
