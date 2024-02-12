package com.example.mockostore.dto.shoppingcart;

import com.example.mockostore.dto.cartitem.CartItemResponseDto;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Set;

@Data
@Accessors(chain = true)
public class ShoppingCartDto {
    private Long id;
    private Long userId;
    private Set<CartItemResponseDto> cartItems;
}
