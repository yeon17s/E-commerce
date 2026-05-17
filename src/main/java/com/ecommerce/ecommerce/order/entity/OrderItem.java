package com.ecommerce.ecommerce.order.entity;

import com.ecommerce.ecommerce.global.exception.BusinessException;
import com.ecommerce.ecommerce.global.exception.ErrorCode;
import com.ecommerce.ecommerce.product.entity.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;
    private int price;

    public OrderItem(Order order, Product product, int quantity) {
        if (quantity <= 0) {
            throw new BusinessException(ErrorCode.INVALID_REQUEST);
        }
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.price = product.getPrice();
    }
}
