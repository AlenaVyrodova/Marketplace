package com.example.mockostore.repository.specification;


import com.example.mockostore.exception.SpecificationNotFoundException;
import com.example.mockostore.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@RequiredArgsConstructor
@Component
public class ProductSpecificationProviderManager implements SpecificationProviderManager<Product> {
    private final List<SpecificationProvider<Product>> productSpecificationProviders;
    @Override
    public SpecificationProvider<Product> getSpecificationProvider(String key) {
        return productSpecificationProviders.stream()
                .filter(p -> p.getKey().equals(key))
                .findFirst()
                .orElseThrow(() -> new SpecificationNotFoundException(
                        "Can't find  specification provider with key" + key));
    }
}
