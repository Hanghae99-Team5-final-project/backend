package com.sparta.team5finalproject.security;

import com.sparta.team5finalproject.security.UserDetailsImpl;
import com.sparta.team5finalproject.security.jwt.JwtTokenUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FormLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    public static final String AUTH_HEADER = "Authorization";
    public static final String TOKEN_TYPE = "BEARER";

    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response,
                                        final Authentication authentication) {
        System.out.println("폼로그인서세스핸들러1");
        final UserDetailsImpl userDetails = ((UserDetailsImpl) authentication.getPrincipal());
        System.out.println("폼로그인서세스핸들러2");
        // Token 생성
        final String token = JwtTokenUtils.generateJwtToken(userDetails);
        System.out.println("폼로그인서세스핸들러3");
        response.addHeader(AUTH_HEADER, TOKEN_TYPE + " " + token);
    }

}
