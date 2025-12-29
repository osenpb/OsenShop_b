package com.osen.ecommerce.core.product.dtos;

import com.osen.ecommerce.core.category.dto.CategoryResponse;

public record ProductRequest(
        String name,
        String description,
        Double price,
        Integer stock,
        //Boolean isActive,
        String imageUrl,
        Long categoryId
) {
}
