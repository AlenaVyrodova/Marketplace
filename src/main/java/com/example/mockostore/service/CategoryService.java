package com.example.mockostore.service;

import com.example.mockostore.dto.category.CategoryDto;
import com.example.mockostore.dto.category.CreateCategoryRequestDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> findAll(Pageable pageable);
    CategoryDto getById(Long id);
    CategoryDto save(@Valid CreateCategoryRequestDto categoryDto);
    CategoryDto update(Long id, CreateCategoryRequestDto categoryRequestDto);
    void deleteById(Long id);

}