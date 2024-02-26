package com.example.mockostore.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.Set;


@Data
@Entity
@SQLDelete(sql = "UPDATE books SET is_deleted=true WHERE id=?")
@Where(clause = "is_deleted=false")
@Table(name = "shopping_carts")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @MapsId
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "shoppingCart")
    private Set<CartItem> cartItems;

    @Column(nullable = false)
    private boolean isDeleted = false;
    public void addCartItem(CartItem cartItem) {
        cartItems.add(cartItem);
        cartItem.setShoppingCart(this);
    }

    public void removeCartItem(CartItem cartItem) {
        cartItems.remove(cartItem);
        cartItem.setShoppingCart(null);
    }
}
