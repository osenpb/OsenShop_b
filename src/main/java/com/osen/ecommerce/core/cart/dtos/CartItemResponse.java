package com.osen.ecommerce.core.cart.dtos;

import com.osen.ecommerce.core.product.dtos.ProductResponse;

public record CartItemResponse(
        Long id,
        Long cartId,
        ProductResponse productResponse,
        Integer quantity,
        Double subtotal
) {
}
