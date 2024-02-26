package com.example.mockostore.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@RequiredArgsConstructor
@Data
@SQLDelete(sql = "UPDATE books SET is_deleted=true WHERE id=?")
@Where(clause = "is_deleted=false")
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    private String description;
    @Column(nullable = false)
    private String pictureUrl;

    private String size;
    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private boolean isDeleted = false;

    @ManyToMany
    @JoinTable(
            name = "products_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();

    @OneToMany(mappedBy = "product", orphanRemoval = true, cascade = CascadeType.REMOVE)
    private Set<CartItem> cartItems;

    @OneToMany(mappedBy = "product", orphanRemoval = true, cascade = CascadeType.REMOVE)
    private Set<OrderItem> orderItems;
}
