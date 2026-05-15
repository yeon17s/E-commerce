package com.ecommerce.ecommerce.cart.dto;

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
}
