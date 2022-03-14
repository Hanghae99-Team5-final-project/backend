package com.sparta.team5finalproject.controller;

import com.sparta.team5finalproject.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.team5finalproject.dto.SignupRequestDto;
//import com.sparta.team5finalproject.service.KakaoUserService;
import com.sparta.team5finalproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
//@Controller
@RestController
public class UserController {

    private final UserService userService;
//    private final KakaoUserService kakaoUserService;

//    @Autowired
//    public UserController(UserService userService, KakaoUserService kakaoUserService) {
//        this.userService = userService;
//        this.kakaoUserService = kakaoUserService;
//    }

    // 회원 로그인 페이지
    @GetMapping("/user/login")
    public String login() {
        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup() {
        System.out.println("회원가입");
        return "signup";
    }

//    // 회원 가입 요청 처리
//    @PostMapping("/user/signup")
//    public String registerUser(SignupRequestDto requestDto) {
//        userService.registerUser(requestDto);
//        return "redirect:/user/login";
//    }

    ////////////////////////////////////////////////////////////
    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public void registerUser(@RequestBody SignupRequestDto requestDto) {
        System.out.println("---------------------");
        userService.registerUser(requestDto);
    }
    ////////////////////////////////////////////////////////////


//    @GetMapping("/user/kakao/callback")
//    public String kakaoLogin(@RequestParam String code) throws JsonProcessingException {
//        kakaoUserService.kakaoLogin(code);
//        return "redirect:/";
//    }
}