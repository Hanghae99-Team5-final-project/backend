package com.sparta.team5finalproject.dto.userDto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class SignupRequestDto {
    // 회원가입 아이디
    private String username;
    // 회원가입 비밀번호
    private String password;
    // 회원가입 이메일
    private String email;
}