package com.pakskiy.news.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewsResponseDto {
    private Long id;
    private String name;
    private String description;
    private String body;
    private String imagePath;
    private String newsStatus;
}