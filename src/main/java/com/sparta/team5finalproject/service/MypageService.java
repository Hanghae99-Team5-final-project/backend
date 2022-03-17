package com.sparta.team5finalproject.service;


import com.sparta.team5finalproject.dto.pageDto.MyCodyResponseDto;
import com.sparta.team5finalproject.dto.pageDto.MyLikeResponseDto;
import com.sparta.team5finalproject.dto.pageDto.MypageResponseDto;
import com.sparta.team5finalproject.dto.pageDto.MypageUpdateRequestDto;
import com.sparta.team5finalproject.model.Cody;
import com.sparta.team5finalproject.model.Likes;
import com.sparta.team5finalproject.model.User;
import com.sparta.team5finalproject.model.Watch;
import com.sparta.team5finalproject.repository.CodyRepository;
import com.sparta.team5finalproject.repository.LikesRepository;
import com.sparta.team5finalproject.repository.UserRepository;
import com.sparta.team5finalproject.repository.WatchRepository;
import com.sparta.team5finalproject.security.provider.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MypageService {
    private final UserRepository userRepository;
    private final CodyRepository codyRepository;
    private final WatchRepository watchRepository;
    private final LikesRepository likesRepository;


    //로그인 된 유저의 마이 페이지
    public MypageResponseDto mypageUserInfo (UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        MypageResponseDto mypageResponseDto = new MypageResponseDto();
        mypageResponseDto.setResult("success");
        mypageResponseDto.setUsername(user.getUsername());
        mypageResponseDto.setEmail(user.getEmail());

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
    public MypageResponseDto updateUserInfo(UserDetailsImpl userDetails, MypageUpdateRequestDto mypageUpdateRequestDto) {
        User user = userDetails.getUser();
//        System.out.println("로그인한 사람의 비밀번호 :" + userDetails.getPassword());
//        System.out.println("확인하기 위한 비밀번호 :" + mypageUpdateRequestDto.getPassword() );
//        if (!userDetails.getPassword().equals(mypageUpdateRequestDto.getPassword())){
//            throw new IllegalArgumentException("비밀번호가 틀립니다.");
//        }
//        if (mypageUpdateRequestDto.getPassword().isEmpty()){
//            throw new IllegalArgumentException("비밀번호를 입력해주세요");
//        }
        System.out.println("회원정보"+user.getEmail());

        System.out.println("바꾸려는 이메일 : " + mypageUpdateRequestDto.getEmail());
        user.update(mypageUpdateRequestDto);

        System.out.println("바뀐 회원 정보"+user.getEmail()); // null
        userRepository.save(user);

        MypageResponseDto mypageResponseDto = new MypageResponseDto();
        mypageResponseDto.setResult("success");
        mypageResponseDto.setUsername(user.getUsername());
        mypageResponseDto.setEmail(user.getEmail());
        System.out.println("회원정보있냐? : "+user.getUsername());
        System.out.println("이메일정보 있나? : "+user.getEmail());
        return mypageResponseDto;
    }


    // 내가 찜한 목록 보기
    @Transactional
    public List<MyLikeResponseDto> getMyLike(UserDetailsImpl userDetails){
        List<Likes> myLikeList = likesRepository.findAllByUserId(userDetails.getUser().getId());
        List<MyLikeResponseDto> myLikeResponseDtoList = new ArrayList<>();
        for (Likes like : myLikeList) {
            Long watchId = like.getWatch().getWatchId();
            Watch watch = watchRepository.findById(watchId).orElseThrow(()-> new IllegalArgumentException("해당 시계글이 존재하지 않습니다."));
            Long likeCount = watch.getLikeCount();
            MyLikeResponseDto myLikeResponseDto = new MyLikeResponseDto(watch,likeCount);
            myLikeResponseDtoList.add(myLikeResponseDto);
        }
        return myLikeResponseDtoList;

    }




    // 내가 올린 코디 전체 보기
    public List<MyCodyResponseDto> getMyCody (UserDetailsImpl userDetails) {
        List<Cody> codyList = codyRepository.findAllByUserId(userDetails.getUser().getId());
        System.out.println("내가 올린 코디 글들을 모아 줬을까? :" + codyList);
        List<MyCodyResponseDto> myCodyResponseDtoList = new ArrayList<>();
        for (Cody cody : codyList) {
            MyCodyResponseDto myCodyResponseDto = new MyCodyResponseDto();
            myCodyResponseDto.setCodyId(cody.getId());
            myCodyResponseDto.setCodyTitle(cody.getCodyTitle());
            myCodyResponseDto.setImageUrl(cody.getImageUrl());
            myCodyResponseDtoList.add(myCodyResponseDto);
        }
        System.out.println("내가 올린 코디 글 리스트 :"+myCodyResponseDtoList);
        return myCodyResponseDtoList;
    }

}
