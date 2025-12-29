package com.osen.ecommerce.core.cart.dtos;

import com.osen.ecommerce.core.cart.models.CartItem;

import java.util.List;

public record CartResponse(
        Long id,
        Long userId,
        List<CartItemResponse> cartItemsResponse,
        Double total
) {
}
