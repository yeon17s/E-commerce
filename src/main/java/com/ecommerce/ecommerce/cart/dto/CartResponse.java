package com.ecommerce.ecommerce.cart.dto;

import com.ecommerce.ecommerce.cart.entity.CartItem;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CartResponse {
    private final Long cartItemId;
    private final Long productId;
    private final String productName;
    private final int price;
    private final int quantity;
    private final int totalPrice;

    public static CartResponse from(CartItem cartItem) {
        return new CartResponse(
                cartItem.getId(),
                cartItem.getProduct().getId(),
                cartItem.getProduct().getName(),
                cartItem.getProduct().getPrice(),
                cartItem.getQuantity(),
                cartItem.getProduct().getPrice() * cartItem.getQuantity()
        );
    }
}
