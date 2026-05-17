package com.ecommerce.ecommerce.cart;

import com.ecommerce.ecommerce.cart.dto.CartRequest;
import com.ecommerce.ecommerce.cart.dto.CartResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "장바구니", description = "장바구니 관련 API")
@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    // GET /api/carts
    @Operation(summary = "장바구니 조회", description = "사용자의 장바구니 상품 목록을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "장바구니 조회 성공")
    @GetMapping
    public ResponseEntity<List<CartResponse>> getCart(@RequestParam Long userId) {
        return ResponseEntity.ok(cartService.getCartItems(userId));
    }

    // POST /api/carts/items
    @Operation(summary = "장바구니 상품 추가", description = "상품을 장바구니에 추가합니다.")
    @ApiResponse(responseCode = "201", description = "장바구니 상품 추가 성공")
    @PostMapping("/items")
    public ResponseEntity<CartResponse> addCartItem(
            @RequestParam Long userId,
            @Valid @RequestBody CartRequest request
    ) {
        return ResponseEntity.status(201)
                .body(cartService.addCartItem(userId, request));
    }

    // DELETE /api/carts/items/{cartItemId}
    @Operation(summary = "장바구니 상품 삭제", description = "장바구니 상품을 삭제합니다.")
    @ApiResponse(responseCode = "204", description = "장바구니 상품 삭제 성공")
    @DeleteMapping("/items/{cartItemId}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable Long cartItemId) {
        cartService.deleteCartItem(cartItemId);
        return ResponseEntity.noContent().build();
    }
}