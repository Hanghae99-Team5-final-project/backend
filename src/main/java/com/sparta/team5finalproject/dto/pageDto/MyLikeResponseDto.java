package com.sparta.team5finalproject.dto.pageDto;


import com.sparta.team5finalproject.model.Watch;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class MyLikeResponseDto {

    private Long watchId;
    private String watchImageUrl;
    private String watchBrand;
    private String lowestPrice;
    private Long likeCount;



    public MyLikeResponseDto(Watch watch, Long likeCount) {
        this.watchId = watch.getWatchId();
        this.watchImageUrl = watch.getWatchImageUrl();
        this.watchBrand = watch.getWatchBrand();
        this.lowestPrice = watch.getLowestPrice();
        this.likeCount = likeCount;

    }


//    public MyLikeResponseDto(Long watchId, Long likeCount) {
//        this.watchId = watchId;
//        this.likeCount = likeCount;
//    }

//    public MyLikeResponseDto(Long watchId, Long likeCount) {
//        this.watchId = watchId;
//        this.likeCount = likeCount;
//    }


//    public MyLikeResponseDto(Cody cody) {
//        this.user = user;
//        this.codyTitle = codyTitle;
//        this.watchBrand = watchBrand;
//        this.codyContent = codyContent;
//        this.imageUrl = imageUrl;
//        this.star = star;
//
//    }

//    public MyLikeResponseDto(Long watchId, Long likeCount) {
//        this.watchId = watchId;
//        //카운트
//
//    }
}