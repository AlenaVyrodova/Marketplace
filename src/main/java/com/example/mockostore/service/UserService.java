package com.example.mockostore.service;

import com.example.mockostore.dto.user.UserRegistrationRequestDto;
import com.example.mockostore.dto.user.UserResponseDto;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto requestDto);
}
