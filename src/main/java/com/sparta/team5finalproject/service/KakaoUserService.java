package com.sparta.team5finalproject.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.team5finalproject.dto.userDto.KakaoUserInfoDto;
import com.sparta.team5finalproject.model.User;
import com.sparta.team5finalproject.model.UserRoleEnum;
import com.sparta.team5finalproject.repository.UserRepository;
import com.sparta.team5finalproject.security.jwt.JwtTokenUtils;
import com.sparta.team5finalproject.security.provider.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class KakaoUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public KakaoUserInfoDto kakaoLogin(String code, HttpServletResponse response) throws JsonProcessingException {
        // 1. "인가 코드"로 "액세스 토큰" 요청
        String accessToken = getAccessToken(code);

        // 2. 토큰으로 카카오 API 호출
        KakaoUserInfoDto kakaoUserInfoDto = getKakaoUserInfo(accessToken);

        // 3. 카카오ID로 회원가입 처리
        User kakaoUser = signupKakaoUser(kakaoUserInfoDto);

        // 4. 강제 로그인 처리
        Authentication authentication = forceLoginKakaoUser(kakaoUser);

        // 5. response Header에 JWT 토큰 추가
        kakaoUsersAuthorizationInput(authentication, response);
        return kakaoUserInfoDto;
    }

    private void kakaoUsersAuthorizationInput(Authentication authentication, HttpServletResponse response) {
        // response header에 token 추가
        UserDetailsImpl userDetailsImpl = ((UserDetailsImpl) authentication.getPrincipal());
        String token = JwtTokenUtils.generateJwtToken(userDetailsImpl);
        response.addHeader("Authorization", "BEARER" + " " + token);
    }

    private Authentication forceLoginKakaoUser(User kakaoUser) {
        UserDetails userDetails = new UserDetailsImpl(kakaoUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }

    private User signupKakaoUser(KakaoUserInfoDto kakaoUserInfoDto) {
        // DB 에 중복된 Kakao Id 가 있는지 확인
        Long kakaoId = kakaoUserInfoDto.getId();
        User kakaoUser = userRepository.findByKakaoId(kakaoId)
                .orElse(null);
        if (kakaoUser == null) {
            // 회원가입
            // username: kakao nickname
//            String nickname = kakaoUserInfo.getNickname();

            // password: random UUID
            String password = UUID.randomUUID().toString();
            String encodedPassword = passwordEncoder.encode(password);

            // email: kakao email
//            String email = kakaoUserInfo.getEmail();
//            if (kakaoUserInfoDto.getEmail() != null) {
//                // 생성자사용시
//                kakaoUser = new User(kakaoUserInfoDto.getNickname(), encodedPassword, null, UserRoleEnum.USER, kakaoId, null);
////                // 빌더사용시
////                kakaoUser = new User.Builder(kakaoUserInfoDto.getNickname(), kakaoUserInfoDto.getNickname(), encodedPassword)
////                        .kakaoId(kakaoId)
////                        .profileImg(kakaoUserInfoDto.getThumbnailUrl())
////                        .build();
//            } else {
                // 생성자사용시
                kakaoUser = new User(kakaoUserInfoDto.getNickname(), encodedPassword, null, UserRoleEnum.USER, kakaoId, null);
                // 빌더사용시
//                kakaoUser = new User.Builder(kakaoUserInfoDto.getNickname(), kakaoUserInfoDto.getNickname(), encodedPassword)
//                        .kakaoId(kakaoId)
//                        .build();
//            }
            userRepository.save(kakaoUser);

        }
        return kakaoUser;
    }

    private KakaoUserInfoDto getKakaoUserInfo(String accessToken) throws JsonProcessingException {
        // HTTP Header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HTTP 요청 보내기
        HttpEntity<MultiValueMap<String, String>> kakaoUserInfoRequest = new HttpEntity<>(headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoUserInfoRequest,
                String.class
        );

        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        Long id = jsonNode.get("id").asLong();
        String nickname = jsonNode.get("properties")
                .get("nickname").asText();

//        //thmbnail test
//        String thumbnailUrl = jsonNode.get("properties")
//                .get("thumbnail_image").asText();
//        if (thumbnailUrl != null) {
//            System.out.println("카카오 사용자 정보: " + id + ", " + nickname + ", " + thumbnailUrl);
//            return new KakaoUserInfoDto(id, nickname, thumbnailUrl);
//        }

        System.out.println("카카오 사용자 정보: " + id + ", " + nickname);
        return new KakaoUserInfoDto(id, nickname);

    }

    private String getAccessToken(String code) throws JsonProcessingException {
        // HTTP Header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HTTP Body 생성
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", "df4db68512904146e79531548886c6c0");
        body.add("redirect_uri", "http://localhost:3000/oauth/kakao/callback");
        body.add("code", code);

        // HTTP 요청 보내기
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
                new HttpEntity<>(body, headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        // HTTP 응답 (JSON) -> 액세스 토큰 파싱
        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        return jsonNode.get("access_token").asText();
    }
}
