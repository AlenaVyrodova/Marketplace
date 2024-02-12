package com.example.mockostore.service;

import com.example.mockostore.dto.cartitem.CartItemAddRequestDto;
import com.example.mockostore.dto.cartitem.CartItemQuantityRequestDto;
import com.example.mockostore.dto.shoppingcart.ShoppingCartDto;
import com.example.mockostore.model.ShoppingCart;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;

public interface ShoppingCartService {
    void addItemToCart(Authentication authentication, CartItemAddRequestDto requestDto0);
    ShoppingCartDto getShoppingCart(Authentication authentication);

    void updateCartItemQuantity(Authentication authentication,
                                Long cartItemId,
                                @Valid CartItemQuantityRequestDto requestDto);

    void clearShoppingCart(ShoppingCart shoppingCart);

}
