package com.sparta.team5finalproject.service;

import com.sparta.team5finalproject.dto.DeleteUserRequestDto;
import com.sparta.team5finalproject.dto.SignupRequestDto;
import com.sparta.team5finalproject.model.User;
import com.sparta.team5finalproject.model.UserRoleEnum;
import com.sparta.team5finalproject.repository.UserRepository;
import com.sparta.team5finalproject.security.provider.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";

//    @Autowired
//    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }

    public void registerUser(SignupRequestDto requestDto) {
        // 회원 ID 중복 확인
        String username = requestDto.getUsername();
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        }

        // 패스워드 암호화
        String password = passwordEncoder.encode(requestDto.getPassword());
//        String password = requestDto.getPassword();
        String email = requestDto.getEmail();

        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
//        if (requestDto.getAdmin()==true) {
//            if (!requestDto.getAdminToken().equals(ADMIN_TOKEN)) {
//                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
//            }
//            role = UserRoleEnum.ADMIN;
//        }

        User user = new User(username, password, email, role);
        userRepository.save(user);

//        return user;
    }


    public void deleteUser(DeleteUserRequestDto deleteUserRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails, HttpServletResponse response)
//            throws IOException
    {
        // 삭제할 유저 찾기
        String inputPassword = deleteUserRequestDto.getPassword();
        Optional<User> found = userRepository.findByUsername(userDetails.getUsername());

        // 입력받은 패스워드 암호화
        String password = passwordEncoder.encode(deleteUserRequestDto.getPassword());

        // 입력받은 패스워드와 DB의 패스워드 비교
        if(found.get().getPassword()==password){
            userRepository.delete(found.get());
        }
//        return user;
    }
}