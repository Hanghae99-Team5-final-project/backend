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
    public String operateLikes(Long watchId, UserDetailsImpl userDetails) {
        // watch 상풍이 있는지 확인
        Watch watch = watchRepository.findById(watchId).orElseThrow(
                () -> new NullPointerException("해당 상품이 존재하지 않습니다."));
        User user = userDetails.getUser();
        // 로그인한 유저가 해당 시계 상품에 좋아요를 했는지 여부
        Optional<Likes> optLikes = likesRepository.findByWatchAndUser(watch, userDetails.getUser());
        if(optLikes.isPresent()){
            Likes likes = optLikes.get();
            if (user == null) {
                new NullPointerException("로그인이 필요합니다.");
            } else if (user.getId().equals(likes.getUser().getId())){
                System.out.println("좋아요 삭제");
                likesRepository.delete(likes);
                // watch 테이블의 좋아요 갯수 설정
                Long likeCnt = watch.getLikeCount();
                likeCnt--;
                watch.setLikeCount(likeCnt);
                watchRepository.save(watch);
            } else {
                throw new UnmatchedUserException("해당 유저가 아닙니다.");
            }

            System.out.println("0000000000=");
//        System.out.println(likes.get()); // no value present 에러남
            System.out.println("optLikes equals="+optLikes.equals(null));
            System.out.println("optLikes =="+optLikes==null);
            System.out.println("likes equals="+likes.equals(null));
            System.out.println("likes =="+likes==null);
            System.out.println("1111="+user.equals(likes.getUser()));
            System.out.println("2222="+(user==likes.getUser()));
//        System.out.println("3333="+"a="+user.getId()+"b="+likes.getUser().getId()+"true="+(user.getId()==likes.getUser().getId()));
//        System.out.println("4444="+"a="+user.getId()+"b="+likes.getUser().getId()+"true="+(user.getId().equals(likes.getUser().getId())));

        } else {
            if (user == null) {
                new NullPointerException("로그인이 필요합니다.");
            }
            Likes createLikes = new Likes(userDetails.getUser(), watch);
            likesRepository.save(createLikes);
            // watch 테이블의 좋아요 갯수 설정
            Long likeCnt = watch.getLikeCount();
            likeCnt++;
            watch.setLikeCount(likeCnt);
            watchRepository.save(watch);

        }
        return "{\"result\":\"true\"}";
    }


//    //찜 삭제 (이 메소드 필요없을듯)
//    @Transactional
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


}

