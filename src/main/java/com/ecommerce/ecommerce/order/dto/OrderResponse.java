package com.ecommerce.ecommerce.order.dto;

import com.ecommerce.ecommerce.order.OrderStatus;
import com.ecommerce.ecommerce.order.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderResponse {
    private final Long orderId;
    private final Long userId;
    private final int totalAmount;
    private final OrderStatus orderStatus;

    public static OrderResponse from(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getUserId(),
                order.getTotalPrice(),
                order.getStatus()
        );
    }
}
