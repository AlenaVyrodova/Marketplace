package com.example.mockostore.controller;

import com.example.mockostore.dto.user.UserLoginRequestDto;
import com.example.mockostore.dto.user.UserLoginResponseDto;
import com.example.mockostore.dto.user.UserRegistrationRequestDto;
import com.example.mockostore.dto.user.UserResponseDto;
import com.example.mockostore.security.AuthenticationService;
import com.example.mockostore.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User management", description = "Endpoints for registration User")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final AuthenticationService authService;

    @PostMapping("/registration")
    @Operation(summary = "Register new user", description = "Register new user")
    public UserResponseDto register(@RequestBody @Valid UserRegistrationRequestDto requestDto) {
        return userService.register(requestDto);
    }

    @PostMapping("/login")
    @Operation(summary = "Login to website ", description = "Login user ")
    public UserLoginResponseDto login(@RequestBody @Valid UserLoginRequestDto request) {
        return authService.authenticate(request);
    }
}
