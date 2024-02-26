package com.example.mockostore.dto.order;

import com.example.mockostore.model.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderUpdateRequestStatusDto {
    @NotNull
     private OrderStatus orderStatus;
}
