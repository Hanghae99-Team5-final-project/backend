package com.sparta.team5finalproject.dto.pageDto;


import com.sparta.team5finalproject.model.Watch;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MyLikeResponseDto {

    // 내가 찜(Cart)한 시계 아이디
    private Long watchId;
    // 내가 찜(Cart)한 시계 이미지
    private String watchImageUrl;
    // 내가 찜(Cart)한 시계 제목
    private String watchBrand;
    // 내가 찜(Cart)한 시계 가격
    private String lowestPrice;
    // 찜받은 갯수
    private Long likeCount;


    public MyLikeResponseDto(Watch watch, Long likeCount) {
        this.watchId = watch.getWatchId();
        this.watchImageUrl = watch.getWatchImageUrl();
        this.watchBrand = watch.getWatchBrand();
        this.lowestPrice = watch.getLowestPrice();
        this.likeCount = likeCount;

    }
}