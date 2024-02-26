package com.example.mockostore.service;

import com.example.mockostore.dto.order.OrderCreateRequestShippingAddressDto;
import com.example.mockostore.dto.order.OrderItemResponseDto;
import com.example.mockostore.dto.order.OrderResponseDto;
import com.example.mockostore.dto.order.OrderUpdateRequestStatusDto;
import com.example.mockostore.model.User;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface OrderService {
    List<OrderResponseDto> getAllUsersOrders(User user, Pageable pageable);

    OrderResponseDto updateOrderStatus(Long id, OrderUpdateRequestStatusDto requestDto);

    OrderResponseDto createOrder(User user, OrderCreateRequestShippingAddressDto requestDto);

    List<OrderItemResponseDto> getAllOrderItemsFromOrder(Long userId, Long orderId,
                                                         Pageable pageable);

    OrderItemResponseDto findOrderItemInOrder(Long userId, Long orderId, Long orderItemId);
}
