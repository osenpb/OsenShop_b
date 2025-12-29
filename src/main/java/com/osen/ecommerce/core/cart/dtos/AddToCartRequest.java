package com.osen.ecommerce.core.cart.dtos;

public record AddToCartRequest(
        Long productId,
        Integer quantity
) {
}
