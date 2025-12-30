package com.osen.ecommerce.core.product.dtos;

public record UpdateProductRequest(
        Long id,
        String name,
        String description,
        Double price,
        Integer stock,
        Boolean isActive,
        String imageUrl,
        Long categoryId
) {
}
