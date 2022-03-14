package com.sparta.team5finalproject.service;

import com.sparta.crawl.dto.MyLikeResponseDto;
import com.sparta.crawl.dto.MypageResponseDto;
import com.sparta.crawl.dto.MypageUpdateRequestDto;
import com.sparta.crawl.model.Likes;
import com.sparta.team5finalproject.repository.UserRepository;
import com.sparta.team5finalproject.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MypageService {
    private final MypageRepository mypageRepository;
    private final UserRepository userRepository;
    private final CodyRepository codyRepository;
    private final WatchRepository watchRepository;

    //로그인 된 유저의 마이 페이지
    public MypageResponseDto mypageUserInfo (UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        MypageResponseDto mypageResponseDto = new MypageResponseDto();
        mypageResponseDto.setResult("success");
        mypageResponseDto.setUsername(user.getUsername());

        return mypageResponseDto;
    }

    //마이페이지에서 개인 정보 클릭 후 보여주는 페이지
    public MypageResponseDto showUserInfo(UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        MypageResponseDto mypageResponseDto = new MypageResponseDto();
        mypageResponseDto.setResult("success");
        mypageResponseDto.setUsername(user.getUsername());
        mypageResponseDto.setEmail(user.getEmail());

        return mypageResponseDto;
    }
    //마이페이지에서 개인정보 수정
    @Transactional
    public MypageResponseDto updateUserInfo(UserDetailsImpl userDetails,MypageUpdateRequestDto mypageUpdateRequestDto) {
        User user = userDetails.getUser();
        if (!userDetails.getPassword().equals(mypageUpdateRequestDto.getPassword())){
            throw new IllegalArgumentException("비밀번호가 틀립니다.");
        }
        if (mypageUpdateRequestDto.getPassword().isEmpty()){
            throw new IllegalArgumentException("비밀번호를 입력해주세요");
        }
        user.update(mypageUpdateRequestDto);
        userRepository.save(user);

        MypageResponseDto mypageResponseDto = new MypageResponseDto();
        mypageResponseDto.setResult("success");
        mypageResponseDto.setUsername(user.getUsername);
        mypageResponseDto.setEmail(user.getEmail);

        return mypageResponseDto;
    }


    // 내가 찜한 목록 보기
    public List<MyLikeResponseDto> getMyLike(Long watchId){

        List<Likes> likesList = likeRepository.findAllByWatchId(watchId);

        List<MyLikeResponseDto> myLikeResponseDtoList = new ArrayList<>();

        for (Likes like : likesList) {
            Long watchId = like.getWatchId();

            Watch watch = watchRepository.findByWatchId(watchId).orElseThrow(
                    () -> new IllegalArgumentException("해당 시계가 존재하지 않습니다.")
            );

            Long likeCount = watch.getLikeCount();
            MyLikeResponseDto myLikeResponseDto = new MyLikeResponseDto(watchId, likeCount);
            myLikeResponseDtoList.add(myLikeResponseDto);
        }
        return myLikeResponseDtoList;
    }


    // 내가 올린 코디 전체 보기
    public List<MyLikeResponseDto> getMyCody (Long codyId) {
        List<Cody> codyList = codyRepository.findAllByCodyId(codyId);

        List<MyLikeResponseDto> myLikeResponseDtoList = new ArrayList<>();
        for (Cody cody : codyList) {
            MyLikeResponseDto myLikeResponseDto = new MyLikeResponseDto(cody);
            myLikeResponseDtoList.add(myLikeResponseDto);
        }
        return myLikeResponseDtoList;
    }

}
