package com.sparta.team5finalproject.dto.codyDto;

import lombok.*;

@Getter
public class CodyRequestDto {

    // 코디글 제목
    private String codyTitle;
    // 시계 브랜드
    private String watchBrand;
    // 시계 모델
    private String watchModel;
    // 코디글 내용
    private String codyContent;
    // 코디 평점
    private String star;


    @Builder
    public CodyRequestDto(String codyTitle, String watchBrand, String watchModel, String codyContent, String star) {
        this.codyTitle = codyTitle;
        this.watchBrand = watchBrand;
        this.watchModel = watchModel;
        this.codyContent = codyContent;
        this.star = star;
    }

}
