package com.sparta.team5finalproject.dto;

import lombok.Getter;

@Getter
public class MyLikeResponseDto {
    private Long watchId;
    private String watchImage;
    private String watchBrand;
    private String lowestPrice;

    public MyLikeResponseDto(Cody cody) {
        this.user = user;
        this.codyTitle = codyTitle;
        this.watchBrand = watchBrand;
        this.codyContent = codyContent;
        this.imageUrl = imageUrl;
        this.star = star;

    }

    public MyLikeResponseDto(Long watchId, Long likeCount) {
        this.watchId = watchId;
        //카운트

    }
}
