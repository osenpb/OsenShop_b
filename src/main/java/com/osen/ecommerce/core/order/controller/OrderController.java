package com.osen.ecommerce.core.order.controller;

import com.osen.ecommerce.auth.domain.models.User;
import com.osen.ecommerce.auth.domain.services.UserService;
import com.osen.ecommerce.common.exceptions.EntityNotFound;
import com.osen.ecommerce.core.cart.models.Cart;
import com.osen.ecommerce.core.cart.models.CartItem;
import com.osen.ecommerce.core.cart.service.CartItemService;
import com.osen.ecommerce.core.cart.service.CartService;
import com.osen.ecommerce.core.order.dtos.OrderFormRequest;
import com.osen.ecommerce.core.order.dtos.OrderResponse;
import com.osen.ecommerce.core.order.mappers.OrderMapper;
import com.osen.ecommerce.core.order.models.Order;
import com.osen.ecommerce.core.order.models.OrderItem;
import com.osen.ecommerce.core.order.services.OrderItemService;
import com.osen.ecommerce.core.order.services.OrderService;
import com.osen.ecommerce.core.product.model.Product;
import com.osen.ecommerce.core.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final UserService userService;
    private final ProductService productService;
    private final CartService cartService;
    private final CartItemService cartItemService;
    private final OrderService orderService;
    private final OrderItemService orderItemService;

//    List<OrderResponse> tengo q devolver algo asi
    @GetMapping
    public ResponseEntity<?> getAllOrders() {
       List<Order> orders = orderService.findAll();
       List<OrderResponse> orderResponseList = OrderMapper.toOrderDtoList(orders);
       return ResponseEntity.ok(orderResponseList);
    }

    @PostMapping("/checkout")
    public ResponseEntity<?> procesarPedido(@AuthenticationPrincipal User user, @RequestBody OrderFormRequest orderForm) {

        Order successfulOrder = orderService.processOrder(user, orderForm);
        OrderResponse orderResponse = OrderMapper.toDto(successfulOrder);
        log.info("Compra realizada con exito");
        return ResponseEntity.ok(orderResponse);

    }

    @GetMapping("/order-success/{id}")
    public ResponseEntity<?> confirmacionPedido(@AuthenticationPrincipal User user, @PathVariable Long id) {

        // Validaciones tipicas de seguridad
        Order order = orderService.findById(id);

        if (!order.getUser().getId().equals(user.getId())) {
            return ResponseEntity.status(403).body("Acceso denegado");
        }

        List<OrderItem> orderItems = order.getOrderItemList();

        return ResponseEntity.ok(Map.of(
                "order", order,
                "orderItems", orderItems
        ));

    }

    @GetMapping("/my-orders")
    public ResponseEntity<List<OrderResponse>> ordenesFromUser(@AuthenticationPrincipal User user) {
        User myUser = userService.findById(user.getId());
        List<Order> orders = orderService.findByUser(user);
        List<OrderResponse> orderResponseList = OrderMapper.toOrderDtoList(orders);

        return ResponseEntity.ok(orderResponseList);
    }

}
