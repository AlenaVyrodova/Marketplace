package com.example.mockostore.service.imp;

import com.example.mockostore.dto.cartitem.CartItemAddRequestDto;
import com.example.mockostore.exception.EntityNotFoundException;
import com.example.mockostore.mapper.CartItemMapper;
import com.example.mockostore.model.CartItem;
import com.example.mockostore.repository.CartItemRepository;
import com.example.mockostore.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;
    @Override
    public CartItem save(CartItemAddRequestDto requestDto) {
        return cartItemRepository.save(cartItemMapper.toModel(requestDto));
    }

    @Override
    public void delete(Long cartItemId) {
        if (!cartItemRepository.existsById(cartItemId)) {
            throw new EntityNotFoundException("Can't find cart item with id " + cartItemId);
        }
        cartItemRepository.deleteById(cartItemId);
    }
}
