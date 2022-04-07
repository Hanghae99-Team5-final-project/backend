package com.sparta.team5finalproject.controller;

import com.sparta.team5finalproject.dto.userDto.UserCheckDto;
import com.sparta.team5finalproject.dto.userDto.SignupRequestDto;
//import com.sparta.team5finalproject.service.KakaoUserService;
import com.sparta.team5finalproject.security.provider.UserDetailsImpl;
import com.sparta.team5finalproject.service.KakaoUserService;
import com.sparta.team5finalproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    private final KakaoUserService kakaoUserService;

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public void registerUser(@RequestBody SignupRequestDto requestDto) {
        userService.registerUser(requestDto);
    }


    //로그인 확인
    @GetMapping("/check/user")
    public UserCheckDto getUserCheck(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userService.getUserCheck(userDetails);
    }


    // 회원가입 중복체크
    @PostMapping("/user/redunancy")
    public String checkName(@RequestBody SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();
        String check = userService.signupUsernameCheck(username);
        return check;
    }


//    // 카카오 로그인
//    @GetMapping("/user/kakao/callback")
//    public KakaoUserInfoDto kakaoLogin(@RequestParam String code, HttpServletResponse response) throws JsonProcessingException {
//        return kakaoUserService.kakaoLogin(code, response);
//    }
//
//
//    // 회원 탈퇴
//    @DeleteMapping("/user/delete")
//    public void deleteUser(@RequestBody DeleteUserRequestDto deleteUserRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails, HttpServletResponse response)
//            throws JsonProcessingException
//    {
//        userService.deleteUser(deleteUserRequestDto, userDetails, response);
//    }

}