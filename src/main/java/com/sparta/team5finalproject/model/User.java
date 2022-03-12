package com.sparta.team5finalproject.model;

import com.sparta.team5finalproject.dto.SignupRequestDto;
import com.sparta.team5finalproject.util.Timestamped;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
//@Builder
@NoArgsConstructor
public class User extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

//    @Column
//    private String profileImage;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @Column(unique = true)
    private Long kakaoId;

    @Column(unique = true)
    private Long naverId;

    //  mappedBy : 양뱡향 관계에서 주체가 되는 쪽(Many쪽, 외래키가 있는 쪽)을 정의
    //  cascade : 관계 엔티티의 읽기 전략을 설정.

    //CascadeType.MERGE : 엔티티 상태를 병합 할 때, 이 필드에 보유 된 엔티티도 병합하십시오.
    //CascadeType.REFRESH : 엔티티를 새로 고칠 때, 이 필드에 보유 된 엔티티도 새로 고칩니다
    //CascadeType.REMOVE : 엔티티를 삭제할 때, 이 필드에 보유 된 엔티티도 삭제하십시오.
    //CascadeType.DETACH : 부모 엔티티가 detach()를 수행하게 되면,
    //                     연관된 엔티티도 detach() 상태가 되어 변경사항이 반영되지 않는다.
    //CascadeType.PERSIST : 엔티티를 영속화 할 때 이 필드에 보유 된 엔티티도 유지합니다.
    //CascadeType.ALL : 모든 Cascade 적용


//    public User(SignUpRequestDto signUpRequestDto, String encodedPassword) {
//        this.username = signUpRequestDto.getUsername();
//        this.nickname = signUpRequestDto.getNickname();
//        this.password = encodedPassword;
//    }
//
//    @Builder
//    public User(String username, String password, String nickname, Long kakaoId) {
//        this.username = username;
//        this.password = password;
//        this.nickname = nickname;
//        this.kakaoId = kakaoId;
//    }

    // --------------------------------------------------------------------------------------------용만
    public User(String username, String password, String email, UserRoleEnum role){
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
        if(naverId==null) {
            this.kakaoId = kakaoId;
            this.naverId = null;
        }else if(kakaoId==null) {
            this.kakaoId = null;
            this.naverId = naverId;
        }
    }

}