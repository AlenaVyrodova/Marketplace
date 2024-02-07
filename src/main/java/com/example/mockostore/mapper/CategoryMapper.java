package com.example.mockostore.mapper;

import com.example.mockostore.config.MapperConfig;
import com.example.mockostore.dto.category.CategoryDto;
import com.example.mockostore.dto.category.CreateCategoryRequestDto;
import com.example.mockostore.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, config = MapperConfig.class)
public interface CategoryMapper {
    CategoryDto toDto(Category category);

    Category toEntity(CreateCategoryRequestDto categoryRequestDto);
}
