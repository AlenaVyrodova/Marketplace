package com.example.mockostore.dto.order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class OrderCreateRequestShippingAddressDto {
    @NotBlank
    @Size(min = 2, max = 255)
    private String shippingAddress;
}