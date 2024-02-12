package com.example.mockostore.service;

import com.example.mockostore.dto.user.UserRegistrationRequestDto;
import com.example.mockostore.dto.user.UserResponseDto;
import com.example.mockostore.model.User;
import org.springframework.security.core.Authentication;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto requestDto);
    User getUser(Authentication authentication);
}
