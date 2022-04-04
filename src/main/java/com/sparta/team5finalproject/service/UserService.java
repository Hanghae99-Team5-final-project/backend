package com.sparta.team5finalproject.service;

import com.sparta.team5finalproject.dto.DeleteUserRequestDto;
import com.sparta.team5finalproject.dto.SignupRequestDto;
import com.sparta.team5finalproject.exception.DuplicateUserException;
import com.sparta.team5finalproject.dto.UserCheckDto;
import com.sparta.team5finalproject.model.User;
import com.sparta.team5finalproject.model.UserRoleEnum;
import com.sparta.team5finalproject.repository.UserRepository;
import com.sparta.team5finalproject.security.provider.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";

//    @Autowired
//    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }

    @Transactional
    public void registerUser(SignupRequestDto requestDto) {
            // 회원 ID 중복 확인
            String username = requestDto.getUsername();
            Optional<User> found = userRepository.findByUsername(username);
            if (found.isPresent()) {
                String detailMessage = String.format("DuplicateUserException: Already exist User, Input: %s", username);
                logger.info(detailMessage);
                throw new DuplicateUserException(detailMessage);
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

        try{
            userRepository.save(user);
        } catch(IllegalArgumentException e){
            // 예외처리, 로깅
            String detailMessage = String.format("유저 DB에 create 실패, Input: %s", username);
            logger.info(detailMessage);
            throw new IllegalArgumentException(detailMessage);
        }

//        return user;
    }


    public void deleteUser(DeleteUserRequestDto deleteUserRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails, HttpServletResponse response)
//            throws IllegalArgumentException
    {
        // 삭제할 유저 찾기
        Optional<User> found = userRepository.findByUsername(userDetails.getUsername());

        // 입력받은 패스워드 암호화
        String password = passwordEncoder.encode(deleteUserRequestDto.getPassword());

        // 입력받은 패스워드와 DB의 패스워드 비교
        if(found.get().getPassword()==password){
            try{
                userRepository.delete(found.get());
            } catch(IllegalArgumentException e){
                String detailMessage = String.format("유저 DB에 delete 실패, Input: %s", userDetails.getUsername());
                logger.info(detailMessage);
                throw new IllegalArgumentException(detailMessage);
            }

        }
//        return user;
    }

    // 로그인 확인
    public UserCheckDto getUserCheck(UserDetailsImpl userDetails) {


        if (userDetails != null) {
            boolean check = true;
            return new UserCheckDto(userDetails.getUser().getId(),
                    userDetails.getUser().getUsername(),
                    userDetails.getUser().getEmail(),
                    check);

        } else {
            Long userId = 0L;
            String username = "null";
            String email = "null";
            boolean check = false;
            return new UserCheckDto(userId,username,email,check);

        }

    }

    // 회원가입 아이디 중복확인
    public String signupUsernameCheck (String username) {
        if(userRepository.findByUsername(username).isPresent()) {
            return "false";
        } else {
            return "true";
        }
    }

}