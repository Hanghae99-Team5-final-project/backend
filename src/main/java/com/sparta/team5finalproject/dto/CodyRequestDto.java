package com.sparta.team5finalproject.dto;

import lombok.*;

//@NoArgsConstructor
//@AllArgsConstructor
@Getter
public class CodyRequestDto {
    private String codyTitle;
    private String watchBrand;
    private String watchModel;
    private String codyContent;
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
