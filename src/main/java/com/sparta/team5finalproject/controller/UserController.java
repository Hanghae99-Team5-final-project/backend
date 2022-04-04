package com.sparta.team5finalproject.controller;

import com.sparta.team5finalproject.dto.DeleteUserRequestDto;
import com.sparta.team5finalproject.dto.KakaoUserInfoDto;
import com.sparta.team5finalproject.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.team5finalproject.dto.SignupRequestDto;
//import com.sparta.team5finalproject.service.KakaoUserService;
import com.sparta.team5finalproject.security.provider.UserDetailsImpl;
import com.sparta.team5finalproject.service.KakaoUserService;
import com.sparta.team5finalproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
//@Controller
@RestController
public class UserController {

    private final UserService userService;
    private final KakaoUserService kakaoUserService;

//    @Autowired
//    public UserController(UserService userService, KakaoUserService kakaoUserService) {
//        this.userService = userService;
//        this.kakaoUserService = kakaoUserService;
//    }


    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public void registerUser(@RequestBody SignupRequestDto requestDto) {
        userService.registerUser(requestDto);
    }


    @GetMapping("/user/kakao/callback")
    public KakaoUserInfoDto kakaoLogin(@RequestParam String code, HttpServletResponse response) throws JsonProcessingException {
        return kakaoUserService.kakaoLogin(code, response);
    }


    @DeleteMapping("/user/delete")
    public void deleteUser(@RequestBody DeleteUserRequestDto deleteUserRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails, HttpServletResponse response)
            throws JsonProcessingException
    {
        userService.deleteUser(deleteUserRequestDto, userDetails, response);
    }
}