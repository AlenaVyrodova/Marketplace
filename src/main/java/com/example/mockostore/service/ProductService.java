package com.example.mockostore.service;

import com.example.mockostore.dto.product.CreateProductRequestDto;
import com.example.mockostore.dto.product.ProductDto;
import com.example.mockostore.dto.product.ProductSearchParametersDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    ProductDto save(CreateProductRequestDto requestDto);

    List<ProductDto> findAll(Pageable pageable);

    ProductDto findById(Long id);

    void deleteById(Long id);

    ProductDto updateById(Long id, CreateProductRequestDto productRequestDto);

    List<ProductDto> searchProductByParams(ProductSearchParametersDto productSearchParameters,
                                           Pageable pageable);

    List findProductByCategoryId(Long id, Pageable pageable);
}
