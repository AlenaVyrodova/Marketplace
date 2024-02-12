package com.example.mockostore.controller;

import com.example.mockostore.dto.cartitem.CartItemAddRequestDto;
import com.example.mockostore.dto.cartitem.CartItemQuantityRequestDto;
import com.example.mockostore.dto.shoppingcart.ShoppingCartDto;
import com.example.mockostore.service.CartItemService;
import com.example.mockostore.service.ShoppingCartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Tag(name = "ShoppingCart management", description = "Endpoints for managing shopping carts")
@RequiredArgsConstructor
@RestController
@RequestMapping("/cart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final CartItemService cartItemService;

    @GetMapping
    @Operation(summary = "Get shopping cart", description = "Get shopping cart")
    public ShoppingCartDto getShoppingCart(Authentication authentication) {
        return shoppingCartService.getShoppingCart(authentication);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Add cart item", description = "Add product to shopping cart")
    public void addBookToShoppingCart(Authentication authentication,
                                      @RequestBody @Valid CartItemAddRequestDto requestDto) {
        shoppingCartService.addItemToCart(authentication, requestDto);
    }

    @PutMapping("/cart-items/{cartItemId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update quantity",
            description = "Update quantity of a products in the shopping cart")
    public void updateCartItemQuantity(Authentication authentication, @PathVariable Long cartItemId,
                                       @RequestBody @Valid CartItemQuantityRequestDto requestDto) {
        shoppingCartService.updateCartItemQuantity(authentication, cartItemId, requestDto);
    }

    @DeleteMapping("/cart-items/{cartItemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete cart item by id", description = "Delete cart item by id")
    public void delete(@PathVariable Long cartItemId) {
        cartItemService.delete(cartItemId);
    }
}
