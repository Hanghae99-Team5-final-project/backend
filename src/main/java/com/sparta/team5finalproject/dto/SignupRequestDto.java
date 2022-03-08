package com.sparta.team5finalproject.dto;

import com.sparta.team5finalproject.model.UserRoleEnum;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupRequestDto {
    private String username;
    private String password;
    private String email;
    private boolean admin = false;
    private String adminToken = "";
    private String createdAt;
    private String modifiedAt;
    private String role;
    private String naverId;
    private String kakaoId;

}