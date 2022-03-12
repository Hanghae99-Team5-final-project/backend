package com.sparta.team5finalproject.controller;

<<<<<<< HEAD

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.team5finalproject.dto.SignupRequestDto;
import com.sparta.team5finalproject.model.User;
import com.sparta.team5finalproject.service.KakaoUserService;
=======
import com.sparta.team5finalproject.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.team5finalproject.dto.SignupRequestDto;
//import com.sparta.team5finalproject.service.KakaoUserService;
>>>>>>> 84b6353e75f8e26db9ab1a16ecf6572eddd4bfd1
import com.sparta.team5finalproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
=======
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
//@Controller
@RestController
>>>>>>> 84b6353e75f8e26db9ab1a16ecf6572eddd4bfd1
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
        return "signup";
    }

//    // 회원 가입 요청 처리
//    @PostMapping("/user/signup")
//    public String registerUser(SignupRequestDto requestDto) {
//        userService.registerUser(requestDto);
//        return "redirect:/user/login";
//    }

<<<<<<< HEAD
    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public User registerUser(SignupRequestDto requestDto) {
        return userService.registerUser(requestDto);

    }
=======
    ////////////////////////////////////////////////////////////
    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public void registerUser(@RequestBody SignupRequestDto requestDto) {
        System.out.println("---------------------");
        userService.registerUser(requestDto);
    }
    ////////////////////////////////////////////////////////////

>>>>>>> 84b6353e75f8e26db9ab1a16ecf6572eddd4bfd1

//    @GetMapping("/user/kakao/callback")
//    public String kakaoLogin(@RequestParam String code) throws JsonProcessingException {
//        kakaoUserService.kakaoLogin(code);
//        return "redirect:/";
//    }
}