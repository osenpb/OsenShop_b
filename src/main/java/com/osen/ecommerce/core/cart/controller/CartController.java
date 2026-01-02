package com.osen.ecommerce.core.cart.controller;

import com.osen.ecommerce.auth.domain.models.User;
import com.osen.ecommerce.auth.domain.services.UserService;
import com.osen.ecommerce.core.cart.dtos.AddToCartRequest;
import com.osen.ecommerce.core.cart.dtos.CartItemResponse;
import com.osen.ecommerce.core.cart.dtos.CartResponse;
import com.osen.ecommerce.core.cart.mapper.CartItemMapper;
import com.osen.ecommerce.core.cart.mapper.CartMapper;
import com.osen.ecommerce.core.cart.models.Cart;
import com.osen.ecommerce.core.cart.models.CartItem;
import com.osen.ecommerce.core.cart.service.CartItemService;
import com.osen.ecommerce.core.cart.service.CartService;
import com.osen.ecommerce.core.product.model.Product;
import com.osen.ecommerce.core.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final CartItemService cartItemService;
    private final UserService userService;
    private final ProductService productService;


    @GetMapping
    public ResponseEntity<?> verCarrito(@AuthenticationPrincipal User user) {

        Optional<Cart> cart = cartService.findByUser(user);
        if(cart.isEmpty()) {
            return ResponseEntity.ok(List.of());
        }
        //faltan mas validaciones
        Cart cartFound = cart.get();
        //List<CartItem> cartItems = cart.get().getCartItemList();
        // List<CartItemResponse> cartItemsResponse = CartItemMapper.toListDto(cartItems);
        CartResponse cartResponse = CartMapper.toDto(cartFound);
        log.info("Procesando info del carrito");

        return ResponseEntity.ok(cartResponse);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addToCart(
            @AuthenticationPrincipal User user,
            @RequestBody AddToCartRequest request
    ) {

        cartService.addProduct(user, request.productId(), request.quantity());
        return ResponseEntity.accepted().build();
    }

    // Pudo usarse el metodo anterior para actualizar, ya q realmente hacen lo
    // mismo, pero me confundo con los endpoints
    @PostMapping("/update")
    public ResponseEntity<?> actualizarItems(@AuthenticationPrincipal User user, @RequestParam("itemId") Long cartItemId, @RequestParam("quantity") int quantity) {

        CartItem item = cartItemService.findById(cartItemId);

        if (item != null) {

            if (quantity <= 0) {
                cartItemService.deleteById(item.getId());
                log.info("Item elimina por stock menor a 0");
            } else {
                item.setQuantity(quantity);
                cartItemService.save(item);
                log.info("El item del carrito fue actualizado");
            }
        }

        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> removeToCart(@AuthenticationPrincipal User user, @PathVariable Long id) {

        cartItemService.deleteByIdAndUserId(id, user.getId());
        return ResponseEntity.accepted().build();
    }

}
