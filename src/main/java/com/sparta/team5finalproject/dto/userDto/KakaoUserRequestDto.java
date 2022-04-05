package com.sparta.team5finalproject.dto.userDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KakaoUserRequestDto {
    // 카카오 ID (카카오 기본키)
    private Long id;
    // 카카오 아이디 (이메일)
    private String username;
    // 카카오 닉네임
    private String nickname;
    // 카카오 프로필
    private String profileImage;
    // 카카오 성별
    private String gender;
    // 카카오 연령대
    private String ageRange;
}
