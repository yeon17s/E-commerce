package com.ecommerce.ecommerce.order.entity;

import com.ecommerce.ecommerce.order.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private int totalPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    public Order(Long userId, int totalPrice) {
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.status = OrderStatus.CREATED;
    }

    public void pay() {
        this.status = OrderStatus.PAID;
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
    }
}
