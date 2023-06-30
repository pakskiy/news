package com.pakskiy.news.controller;

import com.pakskiy.news.dto.*;
import com.pakskiy.news.security.jwt.JwtTokenProvider;
import com.pakskiy.news.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> sendSms(@RequestBody @Validated LoginRequestDto request) {
        return ResponseEntity.ok(authService.login(request));
    }
}