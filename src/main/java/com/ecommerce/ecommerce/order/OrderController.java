package com.ecommerce.ecommerce.order;

import com.ecommerce.ecommerce.order.dto.OrderRequest;
import com.ecommerce.ecommerce.order.dto.OrderResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "주문", description = "주문 관련 API")
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "주문 생성", description = "장바구니 항목을 기반으로 주문을 생성합니다.")
    @ApiResponse(responseCode = "201", description = "주문 생성 성공")
    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(
            @RequestParam Long userId,
            @Valid @RequestBody OrderRequest orderRequest
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderService.createOrder(userId, orderRequest));
    }

    @Operation(summary = "주문 상세 조회", description = "특정 주문의 상세 정보를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "주문 상세 조회 성공")
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrder(
            @RequestParam Long userId,
            @PathVariable Long orderId
    ) {
        return ResponseEntity.ok(orderService.getOrder(userId, orderId));
    }
}
