package com.pakskiy.news.dto;

import lombok.Data;
@Data
public class NewsRequestDto {
    private String name;
    private String description;
    private String body;
    private String imagePath;
    private String newsStatus;
}