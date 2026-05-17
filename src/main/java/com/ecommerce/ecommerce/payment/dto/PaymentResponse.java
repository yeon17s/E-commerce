package com.ecommerce.ecommerce.payment.dto;

import com.ecommerce.ecommerce.payment.PaymentStatus;
import com.ecommerce.ecommerce.payment.entity.Payment;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PaymentResponse {
    private final Long paymentId;
    private final Long orderId;
    private final int amount;
    private final PaymentStatus paymentStatus;

    public static PaymentResponse from(Payment payment) {
        return new PaymentResponse(
                payment.getId(),
                payment.getOrderId(),
                payment.getAmount(),
                payment.getStatus()
        );
    }
}
