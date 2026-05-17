package com.ecommerce.ecommerce.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // Product
    PRODUCT_NOT_FOUND("상품을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    OUT_OF_STOCK("재고가 부족합니다.", HttpStatus.BAD_REQUEST),

    // Cart
    CART_NOT_FOUND("장바구니를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    CART_ITEM_NOT_FOUND("장바구니 상품을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),

    // Order
    ORDER_NOT_FOUND("주문을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    EMPTY_ORDER_ITEMS("주문 상품이 비어있습니다.", HttpStatus.BAD_REQUEST),

    // Payment
    ALREADY_PAID("이미 결제된 주문입니다.", HttpStatus.BAD_REQUEST),
    INSUFFICIENT_POINT("포인트가 부족합니다.", HttpStatus.BAD_REQUEST),

    // Common
    INVALID_REQUEST("잘못된 요청입니다.", HttpStatus.BAD_REQUEST),
    INVALID_REQUEST_BODY("요청 본문 형식이 올바르지 않습니다.", HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR("서버 내부 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String message;
    private final HttpStatus status;
}
