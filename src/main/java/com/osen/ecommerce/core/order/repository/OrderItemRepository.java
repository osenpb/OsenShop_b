package com.osen.ecommerce.core.order.repository;

import com.osen.ecommerce.core.order.models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
