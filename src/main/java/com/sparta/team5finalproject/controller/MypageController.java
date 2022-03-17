package com.sparta.team5finalproject.controller;


import com.sparta.team5finalproject.dto.pageDto.MyLikeResponseDto;
import com.sparta.team5finalproject.dto.pageDto.MypageResponseDto;
import com.sparta.team5finalproject.dto.pageDto.MypageUpdateRequestDto;
import com.sparta.team5finalproject.model.Watch;
import com.sparta.team5finalproject.security.provider.UserDetailsImpl;
import com.sparta.team5finalproject.service.MypageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MypageController {
    private final MypageService mypageService;

    //로그인된 유저의 마이 페이지
    @GetMapping("/api/user/mypage")
    private MypageResponseDto mypageUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {

        return mypageService.mypageUserInfo(userDetails);
    }

    //마이페이지에서 개인 정보 클릭 후 보여주는 페이지
    @GetMapping("/api/user/show")
    private MypageResponseDto showUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return mypageService.showUserInfo(userDetails);
    }

    //마이페이지에서 개인정보 수정
    @PutMapping("/api/user/change")
    private MypageResponseDto updateUserInfo(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody MypageUpdateRequestDto mypageUpdateRequestDto
    ) {
        return mypageService.updateUserInfo(userDetails, mypageUpdateRequestDto);
    }


    // 찜한 목록 보기
    @GetMapping("/api/user/like")
    public List<MyLikeResponseDto> getMyLike(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return mypageService.getMyLike(userDetails);
    }

//    // 내가 올린 코디 전체 보기
//    @GetMapping("/api/user/cody")
//    public List<MyLikeResponseDto> myWriteCody(@RequestBody CodyResponseDto codyResponseDto) {
//        Long codyId = codyResponseDto.getCodyId();
//        List<MyLikeResponseDto> writeCody = mypageService.getMyCody(codyId);
//        return writeCody;
//    }

}
