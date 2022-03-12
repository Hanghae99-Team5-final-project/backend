package com.sparta.team5finalproject.security;

import com.sparta.team5finalproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
@EnableGlobalMethodSecurity(securedEnabled = true) // @Secured 어노테이션 활성화
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserRepository userRepository;

    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) {
// h2-console 사용에 대한 허용 (CSRF, FrameOptions 무시)
        web
                .ignoring()
                .antMatchers("/h2-console/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.headers().frameOptions().disable();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // 시큐리티 폼로그인기능 비활성화
                .formLogin().disable()
                // 로그인폼 화면으로 리다이렉트 비활성화
                .httpBasic().disable()
                // UsernamePasswordAuthenticationFilter 단계에서 json로그인과 jwt토큰을 만들어 response 반환
                .addFilter(new JwtAuthenticationFilter(authenticationManager())) // AuthenticationManager
                // BasicAuthenticationFilter 단계에서 jwt토큰 검증
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), userRepository))
                .authorizeRequests()
                // PreFlight 요청 모두 허가
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                // 게시글 작성 인증
                .antMatchers("/post/write").authenticated()
                // 게시글 수정, 삭제 인증
                .antMatchers("/posts/**").authenticated()
                // 댓글 작성 인증
                .antMatchers("/posts/comments/**").authenticated()
                // 댓글 수정, 삭제 인증
                .antMatchers("/comments/**").authenticated()
                // 그 외 요청 모두 허가
                .anyRequest().permitAll()
                .and().cors();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // - (3)
        configuration.addAllowedOrigin("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.addExposedHeader(HttpHeaders.AUTHORIZATION);
        //configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}