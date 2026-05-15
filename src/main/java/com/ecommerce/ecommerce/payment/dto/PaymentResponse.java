package com.ecommerce.ecommerce.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PaymentResponse {
    private final Long paymentId;
    private final Long orderId;
    private final String paymentStatus;
}
