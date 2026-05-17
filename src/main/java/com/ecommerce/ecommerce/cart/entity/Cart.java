package com.ecommerce.ecommerce.cart.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<CartItem> cartItems = new ArrayList<>();

    public Cart(Long userId) {
        this.userId = userId;
    }

    public void addCartItem(CartItem cartItem) {
        cartItems.add(cartItem);
    }
}
