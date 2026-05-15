package com.ecommerce.ecommerce.payment;

import com.ecommerce.ecommerce.payment.dto.PaymentRequest;
import com.ecommerce.ecommerce.payment.dto.PaymentResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@Tag(name = "결제", description = "결제 관련 API")
@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    // POST /api/payments
    @Operation(summary = "결제 요청", description = "주문에 대한 결제를 요청합니다.")
    @ApiResponse(responseCode = "200", description = "결제 요청 성공")
    @PostMapping
    public ResponseEntity<PaymentResponse> processPayment(@Valid @RequestBody PaymentRequest paymentRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
            new PaymentResponse(1L, paymentRequest.getOrderId(), "SUCCESS")
        );
    }
}
