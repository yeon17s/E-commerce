package com.ecommerce.ecommerce.order;

import com.ecommerce.ecommerce.order.dto.OrderRequest;
import com.ecommerce.ecommerce.order.dto.OrderResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "주문", description = "주문 관련 API")
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    // POST /api/orders
    @Operation(summary = "주문 생성", description = "상품 또는 장바구니를 기반으로 주문을 생성합니다.")
    @ApiResponse(responseCode = "201", description = "주문 생성 성공")
    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody OrderRequest orderRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    // GET /api/orders/{orderId}
    @Operation(summary = "주문 상세 조회", description = "특정 주문의 상세 정보를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "주문 상세 조회 성공")
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable Long orderId) {
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
