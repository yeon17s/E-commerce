package com.ecommerce.ecommerce.payment;

import com.ecommerce.ecommerce.global.exception.BusinessException;
import com.ecommerce.ecommerce.global.exception.ErrorCode;
import com.ecommerce.ecommerce.order.OrderRepository;
import com.ecommerce.ecommerce.order.OrderStatus;
import com.ecommerce.ecommerce.order.entity.Order;
import com.ecommerce.ecommerce.payment.dto.PaymentRequest;
import com.ecommerce.ecommerce.payment.dto.PaymentResponse;
import com.ecommerce.ecommerce.payment.entity.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public PaymentResponse processPayment(Long userId, PaymentRequest request) {
        // 1. 주문 조회
        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new BusinessException(ErrorCode.ORDER_NOT_FOUND));

        // 2. 주문 확인
        // 본인이 한 주문인지
        if (!order.getUserId().equals(userId)) {
            throw new BusinessException(ErrorCode.ORDER_NOT_FOUND);
        }
        // CREATED 상태에서만 결제 가능
        // PAID는 ALREADY_PAID, 그 외(CANCELLED 등)는 INVALID_REQUEST로 구분하여 응답
        if (order.getStatus() == OrderStatus.PAID) {
            throw new BusinessException(ErrorCode.ALREADY_PAID);
        }
        if (order.getStatus() != OrderStatus.CREATED) {
            throw new BusinessException(ErrorCode.INVALID_REQUEST);
        }
        // 결제 레코드 기준 중복 방지 (데이터 정합성 이슈에 대한 이중 방어)
        if (paymentRepository.existsByOrderId(request.getOrderId())) {
            throw new BusinessException(ErrorCode.ALREADY_PAID);
        }

        // 3. 결제 처리
        order.pay();

        // 4. 결제 내역 저장
        Payment payment = paymentRepository.save(
                new Payment(order.getId(), userId, order.getTotalPrice(), PaymentStatus.SUCCESS)
        );

        return PaymentResponse.from(payment);
    }
}
