package com.ecommerce.ecommerce.order.dto;

import com.ecommerce.ecommerce.order.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderResponse {
    private final Long orderId;
    private final Long userId;
    private final int totalAmount;
    private final OrderStatus orderStatus;
}
