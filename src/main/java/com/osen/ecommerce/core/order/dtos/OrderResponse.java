package com.osen.ecommerce.core.order.dtos;

import com.osen.ecommerce.core.order.models.OrderItem;

import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(
        Long id,
        Long userId,
        double total,
        String status,
        String shippingAddress,
        LocalDateTime createdAt,
        List<OrderItemResponse> orderItems
) {
}
