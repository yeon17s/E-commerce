package com.ecommerce.ecommerce.payment.entity;

import com.ecommerce.ecommerce.payment.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;
    private Long userId;
    private int amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    public Payment(Long orderId, Long userId, int amount, PaymentStatus status) {
        this.orderId = orderId;
        this.userId = userId;
        this.amount = amount;
        this.status = status;
    }
}
