package com.example.mockostore.repository.specification;

import com.example.mockostore.dto.product.ProductSearchParametersDto;
import com.example.mockostore.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProductSpecificationBuilder implements SpecificationBuilder<Product>{

    private final SpecificationProviderManager<Product> productSpecificationProviderManager;

    @Override
    public Specification<Product> build(ProductSearchParametersDto searchParametersDto) {
        Specification<Product> spec = Specification.where(null);
        if (searchParametersDto.names() != null && searchParametersDto.names().length > 0) {
            spec = spec.and(productSpecificationProviderManager.getSpecificationProvider("name")
                    .getSpecification(searchParametersDto.names()));
        }
        return spec;
    }
}
