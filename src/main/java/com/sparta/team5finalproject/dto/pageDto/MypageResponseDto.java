package com.sparta.team5finalproject.dto.pageDto;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class MypageResponseDto {
    // 내 정보 수정 결과
    private String result;
    // 유저 이름
    private String username;
    // 유저 이메일
    private String email;
}
