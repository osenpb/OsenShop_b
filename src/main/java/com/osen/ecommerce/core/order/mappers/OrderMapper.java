package com.osen.ecommerce.core.order.mappers;

import com.osen.ecommerce.core.order.dtos.OrderResponse;
import com.osen.ecommerce.core.order.models.Order;

import java.util.List;

public class OrderMapper {

    public static OrderResponse toDto(Order order){
        return new OrderResponse(
                order.getId(),
                order.getUser().getId(),
                order.getTotal(),
                order.getStatus(),
                order.getShippingAddress(),
                order.getCreatedAt(),
                OrderItemMapper.toListDto(order.getOrderItemList())
        );
    }

    public static List<OrderResponse> toOrderDtoList(List<Order> orders){
        return orders.stream().map(OrderMapper::toDto).toList();
    }


}
