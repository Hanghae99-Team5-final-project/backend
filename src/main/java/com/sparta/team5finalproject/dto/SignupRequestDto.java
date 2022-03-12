package com.sparta.team5finalproject.dto;

import com.sparta.team5finalproject.model.UserRoleEnum;
<<<<<<< HEAD
import lombok.Getter;
import lombok.Setter;
=======
import lombok.*;
>>>>>>> 84b6353e75f8e26db9ab1a16ecf6572eddd4bfd1

@Setter
@Getter
@RequiredArgsConstructor
public class SignupRequestDto {
    private String username;
    private String password;
    private String email;
<<<<<<< HEAD
    private boolean admin = false;
    private String adminToken = "";
    private String createdAt;
    private String modifiedAt;
    private String role;
    private String naverId;
    private String kakaoId;

=======
//    private Boolean admin; // 원래는 boolean 형이였음.
//    private String adminToken;
//    private String adminToken = "";

//    public SignupRequestDto(String username, String password, String email){
//        this.username = username;
//        this.password = password;
//        this.email = email;
//    }
>>>>>>> 84b6353e75f8e26db9ab1a16ecf6572eddd4bfd1
}