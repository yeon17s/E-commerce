package com.ecommerce.ecommerce.payment;

import com.ecommerce.ecommerce.payment.dto.PaymentRequest;
import com.ecommerce.ecommerce.payment.dto.PaymentResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "결제", description = "결제 관련 API")
@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @Operation(summary = "결제 요청", description = "주문에 대한 결제를 요청합니다.")
    @ApiResponse(responseCode = "200", description = "결제 성공")
    @PostMapping
    public ResponseEntity<PaymentResponse> processPayment(
            @RequestParam Long userId,
            @Valid @RequestBody PaymentRequest request
    ) {
        return ResponseEntity.ok(paymentService.processPayment(userId, request));
    }
}
