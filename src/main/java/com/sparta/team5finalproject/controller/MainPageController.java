package com.sparta.team5finalproject.controller;


import com.sparta.team5finalproject.dto.pageDto.CategoryPageResponesDto;
import com.sparta.team5finalproject.dto.pageDto.MainPageResponseDto;
import com.sparta.team5finalproject.service.MainPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MainPageController {

    private final MainPageService mainPageService;

    //메인페이지 상품 조회
    @GetMapping("/main")
    public MainPageResponseDto getMainPageWatchList() {
        return mainPageService.getMainPageWatchList();
    }


//    //카테고리별 조회 페이지
//    @GetMapping("/api/watch/category")
//    public CategoryPageResponesDto getCoupleWatchPage() {
//        return mainPageService.getCategoryWatch();
//    }

}