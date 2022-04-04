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

    @PostMapping("/api/like/create/{watchId}")
    public String createLikes(@PathVariable Long watchId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return likesService.createLikes(watchId, userDetails);
    }

    @DeleteMapping("/api/like/delete/{likesId}")
    public String deleteLikes(@PathVariable Long likesId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return likesService.deleteLikes(likesId, userDetails);
    }
}