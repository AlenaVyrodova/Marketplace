package com.example.mockostore.dto.product;

import lombok.Data;

import java.util.Set;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private Double price;
    private String description;
    private String pictureUrl;
    private Set<Long> categoriesId;

}
