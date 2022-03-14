package com.sparta.team5finalproject.controller;

import com.sparta.crawl.dto.MainPageResponseDto;
import com.sparta.crawl.dto.WatchDetailResponseDto;
import com.sparta.crawl.service.MainPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MainPageController {

    private final MainPageService mainPageService;

    //전체 상품 조회

    //메인페이지 상품 조회
    @GetMapping("/main")
    public MainPageResponseDto getMainPageWatchList(){
        return mainPageService.getMainPageWatchList();

    }

    //카테고리별 조회 페이지
    @GetMapping("/api/watch/{category}")
    public MainPageResponseDto getCoupleWatchPage() {
        return mainPageService.getCategoryWatch();
    }


    //시계 상세 페이지
    @GetMapping("/api/detail/{watchId}")
    public WatchDetailResponseDto readDetailWatch(@PathVariable Long watchId){
        return mainPageService.readDetailWatch(watchId);
    }

    //커플시계 상세 페이지지
}
