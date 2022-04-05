package com.sparta.team5finalproject.controller;


import com.sparta.team5finalproject.security.provider.UserDetailsImpl;
import com.sparta.team5finalproject.service.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LikesController {

    private final LikesService likesService;

    // 찜 활성
    @PostMapping("/api/like/create/{watchId}")
    public String createLikes(@PathVariable Long watchId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return likesService.createLikes(watchId, userDetails);
    }


    // 찜 비활성
    @DeleteMapping("/api/like/delete/{likesId}")
    public String deleteLikes(@PathVariable Long likesId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return likesService.deleteLikes(likesId, userDetails);
    }

}