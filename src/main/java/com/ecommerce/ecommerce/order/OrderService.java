package com.ecommerce.ecommerce.order;

import com.ecommerce.ecommerce.cart.CartItemRepository;
import com.ecommerce.ecommerce.cart.entity.CartItem;
import com.ecommerce.ecommerce.global.exception.BusinessException;
import com.ecommerce.ecommerce.global.exception.ErrorCode;
import com.ecommerce.ecommerce.order.dto.OrderRequest;
import com.ecommerce.ecommerce.order.dto.OrderResponse;
import com.ecommerce.ecommerce.order.entity.Order;
import com.ecommerce.ecommerce.order.entity.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartItemRepository cartItemRepository;

    @Transactional
    public OrderResponse createOrder(Long userId, OrderRequest request) {
        // 1. 장바구니 항목 조회
        List<CartItem> cartItems = cartItemRepository.findAllById(request.getCartItemIds());
        if (cartItems.isEmpty()) {
            throw new BusinessException(ErrorCode.EMPTY_ORDER_ITEMS);
        }
        // 조회된 수 != 요청한 수 -> 존재하지 않는 cartItemId 포함
        if (cartItems.size() != request.getCartItemIds().size()) {
            throw new BusinessException(ErrorCode.CART_ITEM_NOT_FOUND);
        }
        // 다른 유저의 장바구니 항목 접근 차단
        boolean hasOtherUserItem = cartItems.stream()
                .anyMatch(cartItem -> !cartItem.getCart().getUserId().equals(userId));
        if (hasOtherUserItem) {
            throw new BusinessException(ErrorCode.CART_ITEM_NOT_FOUND);
        }

        // 2. 총 금액 계산
        int totalPrice = cartItems.stream()
                .mapToInt(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();

        // 3. 주문 생성
        Order order = orderRepository.save(new Order(userId, totalPrice));

        // 4. 주문 항목 생성 + 재고 차감 (주문 생성 시점에 차감)
        List<OrderItem> orderItems = cartItems.stream()
                .map(cartItem -> {
                    cartItem.getProduct().decreaseStock(cartItem.getQuantity());
                    OrderItem orderItem = new OrderItem(order, cartItem.getProduct(), cartItem.getQuantity());
                    order.addOrderItem(orderItem);  // 객체 상태 동기화
                    return orderItem;
                })
                .toList();
        orderItemRepository.saveAll(orderItems);
        cartItemRepository.deleteAll(cartItems);

        return OrderResponse.from(order);
    }

    @Transactional(readOnly = true)
    public OrderResponse getOrder(Long userId, Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new BusinessException(ErrorCode.ORDER_NOT_FOUND));

        // 주문 조회 시 타인 주문 접근 차단 (존재 여부도 노출하지 않음)
        if (!order.getUserId().equals(userId)) {
            throw new BusinessException(ErrorCode.ORDER_NOT_FOUND);
        }
        return OrderResponse.from(order);
    }
}
