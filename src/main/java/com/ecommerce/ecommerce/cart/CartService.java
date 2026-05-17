package com.ecommerce.ecommerce.cart;

import com.ecommerce.ecommerce.cart.dto.CartRequest;
import com.ecommerce.ecommerce.cart.dto.CartResponse;
import com.ecommerce.ecommerce.cart.entity.Cart;
import com.ecommerce.ecommerce.cart.entity.CartItem;
import com.ecommerce.ecommerce.global.exception.BusinessException;
import com.ecommerce.ecommerce.global.exception.ErrorCode;
import com.ecommerce.ecommerce.product.ProductRepository;
import com.ecommerce.ecommerce.product.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<CartResponse> getCartItems(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.CART_NOT_FOUND));
        return cart.getCartItems()
                .stream()
                .map(CartResponse::from)
                .toList();
    }

    @Transactional
    public CartResponse addCartItem(Long userId, CartRequest request) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> cartRepository.save(new Cart(userId)));  // 없으면 생성

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new BusinessException(ErrorCode.PRODUCT_NOT_FOUND));

        CartItem cartItem = new CartItem(cart, product, request.getQuantity());
        cart.addCartItem(cartItem);
        CartItem savedCartItem = cartItemRepository.save(cartItem);
        return CartResponse.from(savedCartItem);
    }

    @Transactional
    public void deleteCartItem(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new BusinessException(ErrorCode.CART_ITEM_NOT_FOUND));
        cartItemRepository.delete(cartItem);
    }
}
