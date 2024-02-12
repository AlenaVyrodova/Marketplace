package com.example.mockostore.mapper;

import com.example.mockostore.config.MapperConfig;
import com.example.mockostore.dto.cartitem.CartItemAddRequestDto;
import com.example.mockostore.dto.cartitem.CartItemResponseDto;
import com.example.mockostore.model.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CartItemMapper {
    @Mapping(target = "product.id", source = "productId")
    CartItem toModel(CartItemAddRequestDto requestDto);

    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "productName", source = "product.name")
    public CartItemResponseDto toResponseDto(CartItem cartItem);
}