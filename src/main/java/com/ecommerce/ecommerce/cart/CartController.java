package com.ecommerce.ecommerce.cart;

import com.ecommerce.ecommerce.cart.dto.CartRequest;
import com.ecommerce.ecommerce.cart.dto.CartResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "장바구니", description = "장바구니 관련 API")
@RestController
@RequestMapping("/api/carts")
public class CartController {

    // GET /api/carts
    @Operation(summary = "장바구니 조회", description = "사용자의 장바구니 상품 목록을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "장바구니 조회 성공")
    @GetMapping
    public ResponseEntity<List<CartResponse>> getCart() {
        return ResponseEntity.status(HttpStatus.OK).body(List.of());
    }

    // POST /api/carts/items
    @Operation(summary = "장바구니 상품 추가", description = "상품을 장바구니에 추가합니다.")
    @ApiResponse(responseCode = "201", description = "장바구니 상품 추가 성공")
    @PostMapping("/items")
    public ResponseEntity<CartResponse> addCartItem(@Valid @RequestBody CartRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    // DELETE /api/carts/items/{cartItemId}
    @Operation(summary = "장바구니 상품 삭제", description = "장바구니 상품을 삭제합니다.")
    @ApiResponse(responseCode = "204", description = "장바구니 상품 삭제 성공")
    @DeleteMapping("/items/{cartItemId}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable Long cartItemId) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}