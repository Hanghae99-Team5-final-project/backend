package com.sparta.team5finalproject.service;


import com.sparta.team5finalproject.exception.UnmatchedUserException;
import com.sparta.team5finalproject.exception.GlobalController;
import com.sparta.team5finalproject.model.Likes;
import com.sparta.team5finalproject.model.User;
import com.sparta.team5finalproject.model.Watch;
import com.sparta.team5finalproject.repository.LikesRepository;
import com.sparta.team5finalproject.repository.UserRepository;
import com.sparta.team5finalproject.repository.WatchRepository;
import com.sparta.team5finalproject.security.provider.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LikesService {


    private final LikesRepository likesRepository;
    //쿠팡
    private final WatchRepository watchRepository;

    //찜 생성
    @Transactional
    public String createLikes(Long watchId, UserDetailsImpl userDetails) {
        Watch watch = watchRepository.findById(watchId).orElseThrow(
                () -> new NullPointerException("해당 상품이 존재하지 않습니다."));
        Likes likes = new Likes(userDetails.getUser(), watch);
        likesRepository.save(likes);
        System.out.println("라이크 저장" + likes);
        Long likeCnt = watch.getLikeCount();
        likeCnt++;
        watch.setLikeCount(likeCnt);
        watchRepository.save(watch);
        System.out.println("라이크 카운트 잘 저장되고 있냐?" + likeCnt + watch);
        return "{\"result\":\"true\"}";
    }


    //찜 삭제 (이 메소드 필요없을듯)
    @Transactional
    public String deleteLikes(Long likesId, UserDetailsImpl userDetails) {
        System.out.println("에러냐? " + likesId);
        Likes likes = likesRepository.findById(likesId).orElseThrow(
                () -> new NullPointerException("deleteLike 내부 findByLikesId 오류"));
        Watch watch = watchRepository.findById(likes.getWatch().getWatchId()).orElseThrow(
                () -> new NullPointerException("해당 상품이 존재하지 않습니다."));
        Optional<Likes> optLikes = likesRepository.findByWatchAndUser(watch, userDetails.getUser());
        Long userId = 0L;
        if (optLikes.isPresent()) {
            userId = optLikes.get().getUser().getId();
        }

        System.out.println("에러냐2? " + userId);

        if (userId.equals(userDetails.getUser().getId())) {
            System.out.println("likes의 userid? " + likes.getUser().getId());
            likesRepository.deleteById(likesId);
            System.out.println("라이크 잘지워지고 있냐?" + likes);
            Long likeCnt = watch.getLikeCount();
            likeCnt--;
            watch.setLikeCount(likeCnt);
            watchRepository.save(watch);
            System.out.println("라이크 카운트 잘 취소되고있냐?" + likeCnt + watch);
        }

        return "{\"result\":\"false\"}";
    }

}



//    public String deleteLikes(Long likesId, UserDetailsImpl userDetails) {
//        System.out.println("에러냐? "+likesId);
//        User user = userDetails.getUser();
//        Likes likes = likesRepository.findById(likesId).orElseThrow(
//                ()->new NullPointerException("deleteLike 내부 findByLikesId 오류"));
//        Watch watch = watchRepository.findById(likes.getWatch().getId()).orElseThrow(
//                () -> new NullPointerException("해당 상품이 존재하지 않습니다."));
//
//        System.out.println("1111="+user.equals(likes.getUser()));
//        System.out.println("2222="+(user==likes.getUser()));
//        System.out.println("3333="+"a="+user.getId()+"b="+likes.getUser().getId()+"true="+(user.getId()==likes.getUser().getId()));
//        System.out.println("4444="+"a="+user.getId()+"b="+likes.getUser().getId()+"true="+(user.getId().equals(likes.getUser().getId())));
//
//        if(user==null){
//            new NullPointerException("로그인이 필요합니다.");
//        } else if (user.getId().equals(likes.getUser().getId())){
//            System.out.println("좋아요 삭제");
//            likesRepository.delete(likes);
//            // watch 테이블의 좋아요 갯수 설정
//            Long likeCnt = watch.getLikeCount();
//            likeCnt--;
//            watch.setLikeCount(likeCnt);
//            watchRepository.save(watch);
//        } else {
//            throw new UnmatchedUserException("해당 유저가 아닙니다.");
//        }
//        return "{\"result\":\"true\"}";
//    }




