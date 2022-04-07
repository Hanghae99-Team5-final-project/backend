package com.sparta.team5finalproject.controller;

import com.sparta.team5finalproject.dto.watchDto.WatchDetailLikeResponseDto;
import com.sparta.team5finalproject.dto.watchDto.WatchDetailResponseDto;
import com.sparta.team5finalproject.security.provider.UserDetailsImpl;
import com.sparta.team5finalproject.service.CoupleWatchService;
import com.sparta.team5finalproject.service.WatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class WatchController {

    private final WatchService watchService;
    private final CoupleWatchService coupleWatchService;

    // 쿠팡 크롤링
    @PostMapping("/cpWatch")
    public void createWatch() throws IOException {
        watchService.cpWatchCrawling();
    }


    // 무신사 크롤링
    @PostMapping("/msWatch")
    public void msWatchCrawling() throws IOException {
        coupleWatchService.msWatchCrawling();
    }


    //시계 상세 페이지
    @GetMapping("/api/detail/{watchId}")
    public WatchDetailResponseDto readDetailWatch(@PathVariable Long watchId) {
        return watchService.readDetailWatch(watchId);
    }


    //시계 상세 페이지 좋아요 조회
    @GetMapping("/api/like/{watchId}")
    public WatchDetailLikeResponseDto readDetailWatchLike(@PathVariable Long watchId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return watchService.readDetailWatchLike(watchId, userDetails);
    }

}
