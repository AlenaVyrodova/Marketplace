package com.example.mockostore.service.imp;

import com.example.mockostore.dto.category.CategoryDto;
import com.example.mockostore.dto.category.CreateCategoryRequestDto;
import com.example.mockostore.exception.EntityNotFoundException;
import com.example.mockostore.mapper.CategoryMapper;
import com.example.mockostore.model.Category;
import com.example.mockostore.repository.CategoryRepository;
import com.example.mockostore.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    @Override
    public List findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable).stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    @Override
    public CategoryDto getById(Long id) {
        return categoryMapper.toDto(categoryRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Can't find category with such  id %d"
                        .formatted(id))));
    }

    @Override
    public CategoryDto save(CreateCategoryRequestDto categoryRequestDto) {
        return categoryMapper.toDto(categoryRepository.save(categoryMapper
                .toEntity(categoryRequestDto)));
    }

    @Override
    public CategoryDto update(Long id, CreateCategoryRequestDto categoryRequestDto) {
        if (!categoryRepository.existsById(id)) {
            throw new EntityNotFoundException("There is not product in db by id %d"
                    .formatted(id));
        }
        Category updatedCategory = (categoryMapper.toEntity(categoryRequestDto));
        updatedCategory.setId(id);
        return categoryMapper.toDto(categoryRepository.save(updatedCategory));
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}

