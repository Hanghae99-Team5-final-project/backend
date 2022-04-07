package com.sparta.team5finalproject.dto.userDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class KakaoUserInfoDto {
    // 카카오 아이디
    private Long id;
    // 카카오 닉네임
    private String nickname;
}
