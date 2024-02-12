package com.example.mockostore.service.imp;

import com.example.mockostore.dto.user.UserRegistrationRequestDto;
import com.example.mockostore.dto.user.UserResponseDto;
import com.example.mockostore.exception.EntityNotFoundException;
import com.example.mockostore.exception.RegistrationException;
import com.example.mockostore.mapper.UserMapper;
import com.example.mockostore.model.Role;
import com.example.mockostore.model.User;
import com.example.mockostore.repository.RoleRepository;
import com.example.mockostore.repository.UserRepository;
import com.example.mockostore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RegistrationException("Can't register user");
        }
        User user = userMapper.toModel(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.getRoles().add(roleRepository.findByName(Role.RoleName.ROLE_USER));
        return userMapper.toUserResponseDto(userRepository.save(user));
    }

    @Override
    public User getUser(Authentication authentication) {
        return userRepository.findByEmail(authentication.getName()).orElseThrow(() ->
                new EntityNotFoundException("Can't find user by email" + authentication.getName()));
    }
}
