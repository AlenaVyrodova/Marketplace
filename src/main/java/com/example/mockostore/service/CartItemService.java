package com.example.mockostore.service;

import com.example.mockostore.dto.cartitem.CartItemAddRequestDto;
import com.example.mockostore.model.CartItem;

public interface CartItemService {
    CartItem save(CartItemAddRequestDto requestDto);

    void delete(Long cartItemId);
}
