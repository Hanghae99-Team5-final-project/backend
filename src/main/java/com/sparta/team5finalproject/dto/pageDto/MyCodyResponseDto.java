package com.sparta.team5finalproject.dto.pageDto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MyCodyResponseDto {
    // 내가 올린 코디 아이디
    private Long codyId;
    // 내가 올린 코디글 제목
    private String codyTitle;
    // 내가 올린 코디글 이미지
    private String imageUrl;
}


