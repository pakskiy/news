package com.pakskiy.news.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CodeConfRequestDto {
    @NotBlank
    private String phone;
    @NotBlank
    private String code;
}
