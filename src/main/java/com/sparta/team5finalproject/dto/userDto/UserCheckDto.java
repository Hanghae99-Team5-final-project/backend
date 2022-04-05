package com.sparta.team5finalproject.dto.userDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UserCheckDto {
    // 유저 아이디
    private Long userId;
    // 유저 이름
    private String username;
    // 유저 이메일
    private String email;
    // 로그인한 유저의 정보 체크
    private boolean check;
}
