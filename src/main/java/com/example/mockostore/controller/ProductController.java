package com.example.mockostore.controller;

import com.example.mockostore.dto.product.CreateProductRequestDto;
import com.example.mockostore.dto.product.ProductDto;
import com.example.mockostore.dto.product.ProductSearchParametersDto;
import com.example.mockostore.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Product management", description = "Endpoints for managing products")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/products")
public class ProductController {
    private final ProductService productService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    @Operation(summary = "Get all products", description = "Get a list of all available products")
    public List<ProductDto> getAll(Pageable pageable) {
        return productService.findAll(pageable);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}")
    @Operation(summary = "get product by id", description = "get the specified product by id ")
    public ProductDto getBookById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new product", description = "Create a new product")
    public ProductDto createBook(@RequestBody @Valid CreateProductRequestDto productDto) {
        return productService.save(productDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    @Operation(summary = "update product by id", description = "update product by id")
    public ProductDto updateBookById(@PathVariable Long id,
                                  @RequestBody @Valid CreateProductRequestDto productRequestDto) {
        return productService.updateById(id, productRequestDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete product by id", description = "Delete specified product by id")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/search")
    public List<ProductDto> searchProducts(ProductSearchParametersDto productSearchParameters,
                                           Pageable pageable) {
        return productService.searchProductByParams(productSearchParameters, pageable);
    }
}
