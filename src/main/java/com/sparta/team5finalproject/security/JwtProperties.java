package com.sparta.team5finalproject.security;

public interface JwtProperties {
    // 시크릿 키
    String secretKey = "oursecretkey";
    // 토큰 유효 시간 (60000밀리초 * 60분 * 24시간 * 3일)
    Long tokenValidTime = 60000*60*24*3L;
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
}
