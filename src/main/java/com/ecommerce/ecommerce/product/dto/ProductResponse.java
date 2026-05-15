package com.ecommerce.ecommerce.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductResponse {
    private final Long productId;
    private final String name;
    private final int price;
    private final int stockQuantity;
}
