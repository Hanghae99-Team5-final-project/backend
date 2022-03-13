package com.sparta.team5finalproject.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.team5finalproject.dto.LoginRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


// 스프링 시큐리티에서 필터체인중 UsernamePasswordAuthenticationFilter 가 있음
// /login 요청해서 username과 password 전송하면 (post)
// UsernamePasswordAuthenticationFilter가 동작함
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    // /login 요청을 하면 로그인 시도를 위해서 실행되는 함수
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("JwtAuthenticationFilter : 로그인 시도중");
        // 1. username과 password를 받아서 Object에 넣기
        ObjectMapper om = new ObjectMapper();
        LoginRequestDto requestDto = null;
        try {
            requestDto = om.readValue(request.getInputStream(), LoginRequestDto.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(requestDto.getUsername(), requestDto.getPassword());
        // loadUserByUsername 실행됨
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        System.out.println("로그인 완료됨 :" + userDetails.getUser().getUsername()); // 값이 나오면 로그인이 정상적으로 되었다는 뜻.
        return authentication;
    }

    // attemptAuthentication 실행 후 인증이 정상적으로 되었으면 successfulAuthentication 함수가 실행됨.
    // JWT토큰을 만들어서 request 요청한 사용자에게 JWT토큰을 response해주면 됨.
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        System.out.println("successfulAuthentication실행됨 : 인증완료되었다는 뜻.");
        UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();

        // Hash암호방식
        String jwtToken = JWT.create()
                // 토큰이름?
                .withSubject("jwtToken : "+userDetails.getUser().getUsername())
                // 유효시간
                .withClaim("expireDate", new Date(System.currentTimeMillis()+ com.sparta.team5finalproject.security.JwtProperties.tokenValidTime))
                // jwt확인용 username
                .withClaim("username", userDetails.getUser().getUsername())
                // secretkey와 함께 HMAC256 복호화
                .sign(Algorithm.HMAC256(com.sparta.team5finalproject.security.JwtProperties.secretKey));

        response.addHeader(com.sparta.team5finalproject.security.JwtProperties.HEADER_STRING, com.sparta.team5finalproject.security.JwtProperties.TOKEN_PREFIX + jwtToken);
    }
}
