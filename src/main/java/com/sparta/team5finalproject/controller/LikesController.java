package com.sparta.team5finalproject.controller;

import com.sparta.crawl.service.LikesService;
import com.sparta.team5finalproject.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LikesController {

    private final LikesService likesService;

    @PostMapping("/api/like/create/{watchId}")
    public String createLikes(@PathVariable Long watchId,@AuthenticationPrincipal UserDetailsImpl userDetails){
        return likesService.createLikes(watchId);
    }


    @DeleteMapping("/api/like/delete/{watchId}")
    public String deleteLikes(@PathVariable Long watchId,@AuthenticationPrincipal UserDetailsImpl userDetails){
        return likesService.deleteLikes(watchId);
    }

}
