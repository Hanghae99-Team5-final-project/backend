package com.sparta.team5finalproject.model;

import com.sparta.team5finalproject.dto.pageDto.MypageUpdateRequestDto;
import com.sparta.team5finalproject.util.Timestamped;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User extends Timestamped {
    // 유저 고유 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // 유저 아이디
    @Column(nullable = false, unique = true)
    private String username;

    // 유저 패스워드
    @Column(nullable = false)
    private String password;

    // 유저 이메일
    @Column(nullable = false)
    private String email;

    // 일반회원 관리자 구분
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    // 카카오 고유 아이디
    @Column(unique = true)
    private Long kakaoId;

    // 네이버 고유 아이디
    @Column(unique = true)
    private Long naverId;


    public User(String username, String password, String email, UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.kakaoId = null;
        this.naverId = null;
    }


    public User(String username, String password, String email, UserRoleEnum role, Long kakaoId, Long naverId) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        if (naverId == null) {
            this.kakaoId = kakaoId;
            this.naverId = null;
        } else if (kakaoId == null) {
            this.kakaoId = null;
            this.naverId = naverId;
        }
    }


    public void update(MypageUpdateRequestDto mypageUpdateRequestDto) {
        this.email = mypageUpdateRequestDto.getEmail();
    }
}