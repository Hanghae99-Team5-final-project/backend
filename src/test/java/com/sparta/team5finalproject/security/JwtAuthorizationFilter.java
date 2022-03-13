package com.sparta.team5finalproject.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.sparta.team5finalproject.model.User;
import com.sparta.team5finalproject.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

// 시큐리티가 filter들을 가지고 있는데 그 필터중에 BasicAuthenticationFilter 가 있다.
// 권한이나 인증이 필요한 특정 주소를 요청했을 때 위 필터를 무조건 타게 되어있다.
// 만약에 인증이나 권한이 필요한 주소가 아니라면 이 필터를 타지 않음.
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private UserRepository userRepository;


    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
    }


    // 인증이나 권한이 필요한 주소요청이 있을 때 해당 필터를 탐.
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("인증이나 권한이 필요한 주소 요청임");

        String jwtHeader = request.getHeader(com.sparta.team5finalproject.security.JwtProperties.HEADER_STRING);
        System.out.println("jwtHeader : " + jwtHeader);

        // header에서 jwt토큰이 없거나 Bearer 타입이 아니면 다시 필터를 타게함.
        if(jwtHeader == null || !jwtHeader.startsWith(com.sparta.team5finalproject.security.JwtProperties.TOKEN_PREFIX)) {
            chain.doFilter(request,response);
            return;
        }

        //헤더에서 토큰 가져오기
        String jwtToken = request.getHeader(com.sparta.team5finalproject.security.JwtProperties.HEADER_STRING).replace(com.sparta.team5finalproject.security.JwtProperties.TOKEN_PREFIX,"");
        // 토큰에서 유효시간 빼기
        Date expireDate = JWT.require(Algorithm.HMAC256(com.sparta.team5finalproject.security.JwtProperties.secretKey)).build().verify(jwtToken).getClaim("expireDate").asDate();
        System.out.println("토큰 유효시간 : " + expireDate);
        Date now = new Date();
        System.out.println("현재시간 : " + now);
        // 유효시간이 현재시간보다 이전이면 다시 필터를 타게함.
        if (expireDate.before(now)) {
            System.out.println("유효시간 지난 토큰입니다.");
            chain.doFilter(request,response);
            return;
        }

        // JWT토큰에서 username 빼기
        String username = JWT.require(Algorithm.HMAC256(com.sparta.team5finalproject.security.JwtProperties.secretKey)).build().verify(jwtToken).getClaim("username").asString();

        // 서명이 정상적으로 됨
        if(username != null){
            System.out.println("username 정상");
            User userEntity = userRepository.findByUsername(username).orElseThrow(()-> new IllegalArgumentException("유저가 존재하지 않습니다."));

            UserDetailsImpl userDetails = new UserDetailsImpl(userEntity);

            //JWT 토큰 서명을 통해서 서명이 정상이면 Authentication 객체를 만들어준다.
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, null);

            // 강제로 시큐리티 세션에 접근하여 Authentication 객체를 저장.
            SecurityContextHolder.getContext().setAuthentication(authentication);


        }
        chain.doFilter(request, response);
    }


}
