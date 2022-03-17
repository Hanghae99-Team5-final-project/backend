package com.sparta.team5finalproject.controller;

import com.sparta.team5finalproject.dto.WatchDetailResponseDto;
import com.sparta.team5finalproject.model.User;
import com.sparta.team5finalproject.model.Watch;
import com.sparta.team5finalproject.repository.WatchRepository;
import com.sparta.team5finalproject.security.provider.UserDetailsImpl;
import com.sparta.team5finalproject.service.CoupleWatchService;
import com.sparta.team5finalproject.service.MainPageService;
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



    @PostMapping("/cpWatch")
    public void createWatch() throws IOException {
        System.out.println("안되면 안나온다 성원아 ? 정신차려라 아? 성원아 병재형님 말씀 단디 듣고 아?");
        watchService.cpWatchCrawling();
    }

    @PostMapping("/msWatch")
    public void msWatchCrawling() throws IOException {
        System.out.println("테스트");
        coupleWatchService.msWatchCrawling();
    }

    //시계 상세 페이지
    @GetMapping("/api/detail/{watchId}")
    public WatchDetailResponseDto readDetailWatch(@PathVariable Long watchId) {
        return watchService.readDetailWatch(watchId);
    }

//    @PostMapping("/couple")
//    public void createCoupleWatch() throws IOException {
//        System.out.println("안되면 안나온다 성원아 ? 정신차려라 아? 성원아 병재형님 말씀 단디 듣고 아?");
//        coupleWatchService.total();
//    }

}
