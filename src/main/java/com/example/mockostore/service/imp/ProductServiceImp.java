package com.example.mockostore.service.imp;

import com.example.mockostore.dto.product.CreateProductRequestDto;
import com.example.mockostore.dto.product.ProductDto;
import com.example.mockostore.dto.product.ProductSearchParametersDto;
import com.example.mockostore.mapper.ProductMapper;
import com.example.mockostore.model.Product;
import com.example.mockostore.repository.ProductRepository;
import com.example.mockostore.repository.specification.ProductSpecificationBuilder;
import com.example.mockostore.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {

private final ProductRepository productRepository;
private final ProductMapper productMapper;
private final ProductSpecificationBuilder productSpecificationBuilder;

    @Override
    public ProductDto save(CreateProductRequestDto requestDto) {
        Product product = productMapper.toModel(requestDto);
        return productMapper.toDto(productRepository.save(product));
    }

    @Override
    public List<ProductDto> findAll(Pageable pageable) {
        return productRepository.findAll(pageable).stream()
                .map(productMapper::toDto)
                .toList();
    }

    @Override
    public ProductDto findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Can't find product by id " + id));
        return productMapper.toDto(product);
    }

    @Override
    public void deleteById(Long id) {
productRepository.deleteById(id);
    }

    @Override
    public ProductDto updateById(Long id, CreateProductRequestDto productRequestDto) {

        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException("Can't update product by id: " + id);
        }
        Product product = productMapper.toModel(productRequestDto);
        product.setId(id);
        return productMapper.toDto(productRepository.save(product));
    }

    @Override
    public List<ProductDto> searchProductByParams(
            ProductSearchParametersDto productSearchParameters, Pageable pageable) {
        Specification<Product> bookSpecification =productSpecificationBuilder.build(productSearchParameters);
        return productRepository.findAll(bookSpecification).stream()
                .map(productMapper::toDto)
                .toList();
    }

    @Override
    public List findProductByCategoryId(Long id, Pageable pageable) {

        return productRepository.findAllByCategoriesId(id, pageable).stream()
                .map(productMapper::toDtoWithoutCategories)
                .toList();
    }
}
