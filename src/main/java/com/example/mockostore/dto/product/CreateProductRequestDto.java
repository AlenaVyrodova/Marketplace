package com.example.mockostore.dto.product;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public class CreateProductRequestDto {
    @NotNull
    private String name;
    @NotNull
    private Double price;
    @NotNull
    private String description;
    @NotNull
    private String pictureUrl;

    @NotNull
    private String size;
    @NotNull
    private String color;
    @NotEmpty
    private Set<Long> categoriesId;

}
