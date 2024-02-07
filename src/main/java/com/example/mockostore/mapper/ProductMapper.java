package com.example.mockostore.mapper;

import com.example.mockostore.config.MapperConfig;
import com.example.mockostore.dto.product.CreateProductRequestDto;
import com.example.mockostore.dto.product.ProductDto;

import com.example.mockostore.dto.product.ProductDtoWithoutCategoryIds;
import com.example.mockostore.model.Product;
import com.example.mockostore.model.Category;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, config = MapperConfig.class)
public interface ProductMapper {
    ProductDto toDto(Product product);

    Product toModel(CreateProductRequestDto requestDto);

    ProductDtoWithoutCategoryIds toDtoWithoutCategories(Product product);

    @AfterMapping
    default void setCategoriesIds(@MappingTarget ProductDto productDto, Product product) {
        productDto.setCategoriesId(
                product.getCategories()
                        .stream()
                        .map(Category::getId)
                        .collect(Collectors.toSet()));
    }

}
