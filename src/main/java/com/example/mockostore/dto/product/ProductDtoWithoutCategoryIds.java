package com.example.mockostore.dto.product;

import lombok.Data;

@Data
public class ProductDtoWithoutCategoryIds {
    private Long id;
    private String name;
    private Double price;
    private String description;
    private String pictureUrl;
    private String size;
    private String color;
}
