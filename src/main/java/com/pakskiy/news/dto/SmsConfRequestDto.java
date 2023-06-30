package com.pakskiy.news.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SmsConfRequestDto {
    @NotBlank
    private String phone;
}
