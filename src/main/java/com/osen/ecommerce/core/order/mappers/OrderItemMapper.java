package com.osen.ecommerce.core.order.mappers;

import com.osen.ecommerce.core.order.dtos.OrderItemResponse;
import com.osen.ecommerce.core.order.models.OrderItem;
import com.osen.ecommerce.core.product.mapper.ProductMapper;

import java.util.List;

public class OrderItemMapper {

    public static OrderItemResponse toDto(OrderItem orderItem){
        return new OrderItemResponse(
                orderItem.getId(),
                orderItem.getPrice(),
                orderItem.getQuantity(),
                orderItem.getOrder().getId(),
                ProductMapper.toDto(orderItem.getProduct())
        );
    }

    public static List<OrderItemResponse> toListDto(List<OrderItem> orderItems) {
        return orderItems.stream().map(OrderItemMapper::toDto).toList();
    }

}
