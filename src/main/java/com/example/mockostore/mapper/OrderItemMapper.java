package com.example.mockostore.mapper;

import com.example.mockostore.config.MapperConfig;
import com.example.mockostore.dto.order.OrderItemResponseDto;
import com.example.mockostore.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;



@Mapper(config = MapperConfig.class)
public interface OrderItemMapper {
    @Mapping(target = "productId", source = "product.id")
    OrderItemResponseDto toDto(OrderItem orderItem);
}