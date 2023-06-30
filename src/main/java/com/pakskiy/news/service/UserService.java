package com.pakskiy.news.service;

import com.pakskiy.news.dto.UserRequestDto;
import com.pakskiy.news.dto.UserResponseDto;
import com.pakskiy.news.exception.RecordNotFoundException;
import com.pakskiy.news.exception.RecordSaveException;
import com.pakskiy.news.exception.RecordUpdateException;
import com.pakskiy.news.model.*;
import com.pakskiy.news.repository.UserRepository;
import com.pakskiy.news.validator.ValidateEmail;
import com.pakskiy.news.validator.ValidatePhone;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public List<UserResponseDto> getListUser() {
        try {
            List<UserEntity> entityList = userRepository.findAll();
            return entityList.parallelStream()
                    .map(e -> UserResponseDto.builder()
                            .id(e.getId())
                            .email(e.getEmail())
                            .phone(e.getPhone())
                            .userStatus(e.getUserStatus().name())
                            .roleName(e.getRoleName().name())
                            .build())
                    .toList();
        }catch (Exception e){
            log.error("List users filed");
            return Collections.emptyList();
        }

    }
    public void saveUser(UserRequestDto request) {
        try {
            UserEntity user = new UserEntity();
            user.setEmail(new ValidateEmail(request.getEmail()).value());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setPhone(new ValidatePhone(request.getPhone()).value());
            user.setRoleName(UserRole.valueOf(request.getRoleName()));
            user.setUserStatus(UserStatus.valueOf(request.getUserStatus()));
            user.setUpdatedAt(LocalDateTime.now());
            userRepository.save(user);
        }catch (Exception e){
            log.error("Save user filed");
            throw new RecordSaveException("Error save user");
        }
    }
    public UserResponseDto getUserById(Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new RecordNotFoundException("User not found"));
        return UserResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .phone(user.getPhone())
                .roleName(user.getRoleName().name())
                .userStatus(user.getUserStatus().name())
                .build();
    }
    public void updateUser(UserRequestDto request, Long userId) {
        try {
            UserEntity user = new UserEntity();
            user.setId(request.getId());
            user.setEmail(new ValidateEmail(request.getEmail()).value());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setPhone(new ValidatePhone(request.getPhone()).value());
            user.setRoleName(UserRole.valueOf(request.getRoleName()));
            user.setUserStatus(UserStatus.valueOf(request.getUserStatus()));
            user.setUpdatedAt(LocalDateTime.now());
            userRepository.save(user);
        }catch (Exception e){
            log.error("Update user filed");
            throw new RecordUpdateException("Error update user");
        }
    }

    public Optional<UserEntity> getUserByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }

    public Optional<UserEntity> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
