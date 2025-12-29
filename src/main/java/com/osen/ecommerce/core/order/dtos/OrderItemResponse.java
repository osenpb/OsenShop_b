package com.osen.ecommerce.core.order.dtos;

import com.osen.ecommerce.core.product.dtos.ProductResponse;

public record OrderItemResponse(
        Long id,
        Double price,
        Integer quantity,
        Long orderId,
        ProductResponse productResponse
) {
}
