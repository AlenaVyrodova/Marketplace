package com.example.mockostore.repository.specification;

import com.example.mockostore.dto.product.ProductSearchParametersDto;
import org.springframework.data.jpa.domain.Specification;

public interface SpecificationBuilder<T> {
    Specification<T> build(ProductSearchParametersDto searchParametersDto);
}
