package com.pakskiy.news.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequestDto {
    private Long id;
    private String email;
    private String password;
    private String phone;
    private String userStatus;
    private String roleName;
}
