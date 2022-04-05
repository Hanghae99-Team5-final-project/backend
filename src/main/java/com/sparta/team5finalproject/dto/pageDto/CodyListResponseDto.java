package com.sparta.team5finalproject.dto.pageDto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CodyListResponseDto {
    // 코디 아이디
    private Long codyId;
    // 코디글 제목
    private String codyTitle;
    // 시계 브랜드
    private String watchBrand;
    // 시계 모델
    private String watchModel;
    // 코디글 내용
    private String codyContent;
    // 이미지 주소
    private String imageUrl;
    // 코디글 평점
    private String star;
}
