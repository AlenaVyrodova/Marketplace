package com.example.mockostore.repository;

import com.example.mockostore.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.List;

public interface ProductRepository extends
        JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    List<Product> findAllByCategoriesId(Long id, Pageable pageable);
}
