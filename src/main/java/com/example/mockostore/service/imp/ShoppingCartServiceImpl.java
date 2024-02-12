package com.example.mockostore.service.imp;

import com.example.mockostore.dto.cartitem.CartItemAddRequestDto;
import com.example.mockostore.dto.cartitem.CartItemQuantityRequestDto;
import com.example.mockostore.dto.shoppingcart.ShoppingCartDto;
import com.example.mockostore.exception.EntityNotFoundException;
import com.example.mockostore.mapper.ShoppingCartMapper;
import com.example.mockostore.model.CartItem;
import com.example.mockostore.model.Product;
import com.example.mockostore.model.ShoppingCart;
import com.example.mockostore.model.User;
import com.example.mockostore.repository.CartItemRepository;
import com.example.mockostore.repository.ProductRepository;
import com.example.mockostore.repository.ShoppingCartRepository;
import com.example.mockostore.service.ShoppingCartService;

import com.example.mockostore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;
    private final ShoppingCartMapper shoppingCartMapper;
    private final UserService userService;

    @Override
    public void addItemToCart(Authentication authentication, CartItemAddRequestDto requestDto) {
        User user = userService.getUser(authentication);
        ShoppingCart shoppingCart = shoppingCartRepository.findById(user.getId()).orElseThrow(() ->
                new EntityNotFoundException("Can't find shopping cart by id " + user.getId()));
        Product productToAdd = productRepository.findById(requestDto.getProductId()).orElseThrow(
                () -> new EntityNotFoundException("Can't find product with id "
                        + requestDto.getProductId()));
        Optional<CartItem> cartItemFromDB = shoppingCart.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals(requestDto.getProductId()))
                .findFirst();
        CartItem cartItemToUpdate = new CartItem();
        if (cartItemFromDB.isPresent()) {
            cartItemToUpdate = cartItemFromDB.get();
            cartItemToUpdate.setQuantity(cartItemToUpdate.getQuantity() + requestDto.getQuantity());
        } else {
            cartItemToUpdate.setShoppingCart(shoppingCart);
            cartItemToUpdate.setProduct(productToAdd);
            cartItemToUpdate.setQuantity(requestDto.getQuantity());
        }
        cartItemRepository.save(cartItemToUpdate);
    }

    @Override
    public ShoppingCartDto getShoppingCart(Authentication authentication) {
        User user = userService.getUser(authentication);
        ShoppingCart shoppingCart = shoppingCartRepository.findById(user.getId()).orElseThrow(() ->
                new EntityNotFoundException("Can't find shopping cart by id " + user.getId()));
        return shoppingCartMapper.toDto(shoppingCart);
    }

    @Override
    public void updateCartItemQuantity(Authentication authentication, Long cartItemId, CartItemQuantityRequestDto requestDto) {
        User user = userService.getUser(authentication);
        ShoppingCart shoppingCart = shoppingCartRepository.findById(user.getId()).orElseThrow(() ->
                new EntityNotFoundException("Can't find shopping cart by id " + user.getId()));
        CartItem cartItem = cartItemRepository.findByIdAndShoppingCartId(cartItemId,
                        shoppingCart.getId())
                .orElseThrow(() -> new EntityNotFoundException("Can't find cart item by id"
                        + cartItemId));
        cartItem.setQuantity(requestDto.getQuantity());
        cartItemRepository.save(cartItem);
    }

    @Override
    public void clearShoppingCart(ShoppingCart shoppingCart) {
        Set<CartItem> cartItems = shoppingCart.getCartItems();
        for (CartItem cartItem : cartItems) {
            cartItemRepository.deleteById(cartItem.getId());
        }
    }
}
