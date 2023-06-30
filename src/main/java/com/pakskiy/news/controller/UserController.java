package com.pakskiy.news.controller;

import com.pakskiy.news.dto.UserRequestDto;
import com.pakskiy.news.dto.UserResponseDto;
import com.pakskiy.news.model.UserEntity;
import com.pakskiy.news.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;
    @GetMapping("/list")
    public List<UserResponseDto> listUser() {
        return userService.getListUser();
    }

    @PostMapping("/save")
    public ResponseEntity saveUser(@RequestBody UserRequestDto request) {
        userService.saveUser(request);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping("/items/{userId}")
    public ResponseEntity<UserResponseDto> editUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @PutMapping("/items/{userId}")
    public ResponseEntity updateUser(@PathVariable Long userId, @RequestBody UserRequestDto request) {
        userService.updateUser(request, userId);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
}