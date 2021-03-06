package com.sparta.team5finalproject.config;

import com.sparta.team5finalproject.security.provider.FormLoginAuthProvider;
import com.sparta.team5finalproject.security.provider.JWTAuthProvider;
import com.sparta.team5finalproject.security.filter.FormLoginFilter;
import com.sparta.team5finalproject.security.filter.JwtAuthFilter;
import com.sparta.team5finalproject.security.jwt.HeaderTokenExtractor;
import com.sparta.team5finalproject.security.provider.FilterSkipMatcher;
import com.sparta.team5finalproject.security.provider.FormLoginSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity // 스프링 Security 설정들을 활성화
@EnableGlobalMethodSecurity(securedEnabled = true) // @Secured 어노테이션 활성화
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JWTAuthProvider jwtAuthProvider;
    private final HeaderTokenExtractor headerTokenExtractor;

    public WebSecurityConfig(
            JWTAuthProvider jwtAuthProvider,
            HeaderTokenExtractor headerTokenExtractor
    ) {
        this.jwtAuthProvider = jwtAuthProvider;
        this.headerTokenExtractor = headerTokenExtractor;
    }

    @Bean
    public BCryptPasswordEncoder encodePassword() {

        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) {
        auth
                .authenticationProvider(formLoginAuthProvider())
                .authenticationProvider(jwtAuthProvider);
    }

//    @Override
//    public void configure(WebSecurity web) {
//        // h2-console 사용에 대한 허용 (CSRF, FrameOptions 무시)
//        web
//                .ignoring()
//                .antMatchers("/h2-console/**"); // 권한 관리 대상을 지정하는 옵션. URL, HTTP 메소드별로 관리가 가능.
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable() // Http basic Auth  기반으로 로그인 인증창이 뜸.  disable 시에 인증창 뜨지 않음.
                .csrf().disable() // rest api이므로 csrf 보안이 필요없으므로 disable처리.
                .authorizeRequests() //시큐리티 처리에 HttpServletRequest를 이용한다는 것을 의미. URL 별 권한 관리를 설정하는 옵션의 시작점. antMatchers 옵션을 사용하려면 선언되어야 함.
                //.antMatchers();
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll(); //preflight request에 대해, 인증을 하지 않고 요청을 모두 허용

//        http
//                .headers()
//                .frameOptions().sameOrigin(); // SockJS는 기본적으로 HTML iframe 요소를 통한 전송을 허용하지 않도록 설정되는데 해당 내용을 해제한다.

        // cors설정 추가
        http
                .cors()
                .configurationSource(corsConfigurationSource());

        // 서버에서 인증은 JWT로 인증하기 때문에 Session의 생성을 막습니다.
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.headers().frameOptions().disable();

        /*
         * 1.
         * UsernamePasswordAuthenticationFilter 이전에 FormLoginFilter, JwtFilter 를 등록합니다.
         * FormLoginFilter : 로그인 인증을 실시합니다.
         * JwtFilter       : 서버에 접근시 JWT 확인 후 인증을 실시합니다.
         */
        http
                .addFilterBefore(formLoginFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);


        http.authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/api/detail/**").permitAll()
//                .antMatchers("/api/detail/**").anonymous()
                .anyRequest()
                .permitAll()
                .and()
                // 로그아웃 기능
                .logout()
                // 로그아웃 요청 처리 URL
                .logoutUrl("/user/logout")
                .permitAll()
                .and()
                .exceptionHandling()
                // "접근 불가" 페이지 URL 설정
                .accessDeniedPage("/forbidden.html");
    }

    @Bean
    public FormLoginFilter formLoginFilter() throws Exception {
        FormLoginFilter formLoginFilter = new FormLoginFilter(authenticationManager());
        formLoginFilter.setFilterProcessesUrl("/user/login");
        formLoginFilter.setAuthenticationSuccessHandler(formLoginSuccessHandler());
        formLoginFilter.afterPropertiesSet();
        return formLoginFilter;
    }

    @Bean
    public FormLoginSuccessHandler formLoginSuccessHandler() {
        return new FormLoginSuccessHandler();
    }

    @Bean
    public FormLoginAuthProvider formLoginAuthProvider() {
        return new FormLoginAuthProvider(encodePassword());
    }

    private JwtAuthFilter jwtFilter() throws Exception {
        List<String> skipPathList = new ArrayList<>();

        // Static 정보 접근 허용
        skipPathList.add("GET,/images/**");
        skipPathList.add("GET,/css/**");

        // h2-console 허용
        skipPathList.add("GET,/h2-console/**");
        skipPathList.add("POST,/h2-console/**");

        // 회원 관리 API 허용
        skipPathList.add("GET,/user/**");
        skipPathList.add("POST,/user/**");
//        skipPathList.add("OPTIONS,/user/**");
//        skipPathList.add("OPTIONS,/**");
//        skipPathList.add("POST,/user/signup");
//        skipPathList.add("POST,/user/login");

        // 회원가입
        skipPathList.add("POST,/user/signup");

        // 메인페이지
        skipPathList.add("GET,/main/**");

        //카테고리페이지
        skipPathList.add("GET,/api/watch/category/**");

        //시계상세페이지
        skipPathList.add("GET,/api/detail/**");

        //코디 목록
        skipPathList.add("GET,/api/cody/**");

        //코디 상세
        skipPathList.add("GET,/api/cody/detail/**");

        // 회원가입 중복체크
        skipPathList.add("POST,/user/redunancy/**");

        //크롤링
        skipPathList.add("POST,/cpWatch/**");
        skipPathList.add("POST,/msWatch/**");

        // 로깅 테스트
        skipPathList.add("GET,/log");

        // 무중단 배포를 위한 서버 헬스체크
        skipPathList.add("GET,/health");

        skipPathList.add("GET,/forbidden.html");

        skipPathList.add("GET,/basic.js");

        skipPathList.add("GET,/favicon.ico");

        FilterSkipMatcher matcher = new FilterSkipMatcher(
                skipPathList,
                "/**"
        );

        JwtAuthFilter filter = new JwtAuthFilter(
                matcher,
                headerTokenExtractor
        );
        filter.setAuthenticationManager(super.authenticationManagerBean());

        return filter;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOriginPattern("*");
        configuration.setAllowCredentials(true);
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.addExposedHeader("Authorization");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}












//////////////////////////////////////////////////////////////////////////////////////////////
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.httpBasic().disable() // Http basic Auth  기반으로 로그인 인증창이 뜸.  disable 시에 인증창 뜨지 않음.
//                .csrf().disable() // rest api이므로 csrf 보안이 필요없으므로 disable처리.
//                .authorizeRequests() //시큐리티 처리에 HttpServletRequest를 이용한다는 것을 의미
//                //.antMatchers();
//                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll(); //preflight request에 대해, 인증을 하지 않고 요청을 모두 허용
//
//        //preflight request이란 사전요청으로 서버에서 브라우저한테 어떤 Origin과 Method에 대하여 접근을 허용하는지 알려줍니다
//
//        // 서버에서 인증은 JWT로 인증하기 때문에 Session의 생성을 막습니다.
//        http
//                .cors() // spring-security에서 cors를 적용한다는 설정, 인증 성공 여부와 무관하게 Origin 헤더가 있는 모든 요청에 대해 CORS 헤더를 포함한 응답을 해줌
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS); //jwt token으로 인증하므로 stateless 하도록 처리.
//
//        http.headers().frameOptions().disable();
//        /*
//         * 1.
//         * UsernamePasswordAuthenticationFilter 이전에 FormLoginFilter, JwtFilter 를 등록합니다.
//         * FormLoginFilter : 로그인 인증을 실시합니다.
//         * JwtFilter       : 서버에 접근시 JWT 확인 후 인증을 실시합니다.
//         */
//        http
//                .addFilterBefore(formLoginFilter(), UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
//
//        http.authorizeRequests()
//                .anyRequest()
//                .permitAll()
//                .and()
//                // [로그아웃 기능]
//                .logout()
//                // 로그아웃 요청 처리 URL
//                .logoutUrl("/user/logout")
//                .permitAll()
//                .and()
//                .exceptionHandling()
//                // "접근 불가" 페이지 URL 설정
//                .accessDeniedPage("/forbidden.html");
//    }
//
//    @Bean
//    public FormLoginFilter formLoginFilter() throws Exception {
//        FormLoginFilter formLoginFilter = new FormLoginFilter(authenticationManager());
//        formLoginFilter.setFilterProcessesUrl("/user/login");
//        formLoginFilter.setAuthenticationSuccessHandler(formLoginSuccessHandler());
//        formLoginFilter.afterPropertiesSet();
//        return formLoginFilter;
//    }
//
//    @Bean
//    public FormLoginSuccessHandler formLoginSuccessHandler() {
//        return new FormLoginSuccessHandler();
//    }
//
//    @Bean
//    public FormLoginAuthProvider formLoginAuthProvider() {
//        return new FormLoginAuthProvider(encodePassword());
//    }
//
//    private JwtAuthFilter jwtFilter() throws Exception {
//        List<String> skipPathList = new ArrayList<>();
//        //로그인 유무가 걸러지는 곳.
//        // Static 정보 접근 허용
//        // 토큰 없이, 로그인 없이도 보여줄 수 있는 권한
//        skipPathList.add("GET,/images/**");
//        skipPathList.add("GET,/css/**");
//
//        // h2-console 허용
//        skipPathList.add("GET,/h2-console/**");
//        skipPathList.add("POST,/h2-console/**");
//        // 회원 관리 API 허용
//        skipPathList.add("GET,/user/**");
//        skipPathList.add("POST,/user/**");
////        skipPathList.add("GET,/move/**");
////        // 주식검색하기 API 허용
////        skipPathList.add("GET,/stock/**");
////        // 백테스팅 결과 API 허용
////        skipPathList.add("POST,/port/result");
////        //포트폴리오 상세보기 허용
////        skipPathList.add("GET,/port/details/**");
////
////
////        skipPathList.add("GET,/test");
//
//        skipPathList.add("GET,/");
//        skipPathList.add("GET,/basic.js");
//
//        skipPathList.add("GET,/favicon.ico");
//
//        FilterSkipMatcher matcher = new FilterSkipMatcher(
//                skipPathList,
//                "/**"
//        );
//
//        JwtAuthFilter filter = new JwtAuthFilter(
//                matcher,
//                headerTokenExtractor
//        );
//        filter.setAuthenticationManager(super.authenticationManagerBean());
//
//        return filter;
//    }
//
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//}