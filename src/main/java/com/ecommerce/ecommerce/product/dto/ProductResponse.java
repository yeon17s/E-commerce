package com.ecommerce.ecommerce.product.dto;

import com.ecommerce.ecommerce.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductResponse {
    private final Long productId;
    private final String name;
    private final int price;
    private final int stockQuantity;

    public static ProductResponse from(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getStock()
        );
    }
}
