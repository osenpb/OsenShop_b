package com.osen.ecommerce.core.cart.repositories;

import com.osen.ecommerce.auth.domain.models.User;
import com.osen.ecommerce.core.cart.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);
}
