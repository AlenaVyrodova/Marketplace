package com.example.mockostore.dto.order;

import lombok.Data;

@Data
public class OrderItemResponseDto {
    private Long id;
    private Long productId;
    private int quantity;
}