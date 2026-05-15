package com.ecommerce.ecommerce.product;

import com.ecommerce.ecommerce.product.dto.ProductResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "상품", description = "상품 관련 API")
@RestController
@RequestMapping("/api/products")
public class ProductController {

    // GET /api/products
    @Operation(summary = "상품 목록 조회", description = "판매 중인 상품 목록을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "상품 목록 조회 성공")
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(List.of());
    }

    // GET /api/products/{productId}
    @Operation(summary = "상품 상세 조회", description = "특정 상품의 상세 정보를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "상품 상세 조회 성공")
    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProductDetail(@PathVariable Long productId) {
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    // GET /api/products/popular
    @Operation(summary = "인기상품 조회", description = "주문 데이터를 기준으로 인기상품 목록을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "인기상품 조회 성공")
    @GetMapping("/popular")
    public ResponseEntity<List<ProductResponse>> getPopularProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(List.of());
    }
}