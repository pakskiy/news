package com.pakskiy.news.controller;

import com.pakskiy.news.dto.CodeConfRequestDto;
import com.pakskiy.news.dto.CodeConfResponseDto;
import com.pakskiy.news.dto.SmsConfRequestDto;
import com.pakskiy.news.service.ConfirmationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public")
@AllArgsConstructor
public class ConfirmationController {
    private ConfirmationService confirmationService;
    @PostMapping("/sms")
    public ResponseEntity<Void> sendSms(@RequestBody @Validated SmsConfRequestDto request) {
        confirmationService.send(request.getPhone());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/validate")
    public ResponseEntity<CodeConfResponseDto> validateSms(@RequestBody @Validated CodeConfRequestDto request) {
        String sessionId = confirmationService.validate(request.getPhone(), request.getCode());
        return ResponseEntity.ok(CodeConfResponseDto.builder()
                .sessionId(sessionId)
                .build());
    }
}
