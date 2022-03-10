package com.sparta.team5finalproject.dto;

import com.sparta.team5finalproject.model.UserRoleEnum;
import lombok.*;

@Setter
@Getter
@RequiredArgsConstructor
public class SignupRequestDto {
    private String username;
    private String password;
    private String email;
//    private Boolean admin; // 원래는 boolean 형이였음.
//    private String adminToken;
//    private String adminToken = "";

//    public SignupRequestDto(String username, String password, String email){
//        this.username = username;
//        this.password = password;
//        this.email = email;
//    }
}