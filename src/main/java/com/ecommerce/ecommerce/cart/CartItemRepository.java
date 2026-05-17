package com.ecommerce.ecommerce.cart;

import com.ecommerce.ecommerce.cart.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
