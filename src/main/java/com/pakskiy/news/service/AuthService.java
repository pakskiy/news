package com.pakskiy.news.service;

import com.pakskiy.news.dto.LoginRequestDto;
import com.pakskiy.news.dto.LoginResponseDto;
import com.pakskiy.news.exception.RecordNotFoundException;
import com.pakskiy.news.model.UserEntity;
import com.pakskiy.news.repository.UserRepository;
import com.pakskiy.news.security.jwt.JwtTokenProvider;
import com.pakskiy.news.validator.ValidateEmail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public LoginResponseDto login(LoginRequestDto request) {
        String username = request.getEmail();
//        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, request.getPassword()));

        UserEntity user = userRepository.getUserForLogin(new ValidateEmail(request.getEmail()).value(), request.getSessionId())
                .orElseThrow(() -> new RecordNotFoundException("User not found or wrong credentials"));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new RecordNotFoundException("User not found or wrong credentials");
        }

        String token = jwtTokenProvider.createToken(username, user.getRoleName().name());


        ///here need token generator

        return LoginResponseDto.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .token(token)
                .build();
    }
}
