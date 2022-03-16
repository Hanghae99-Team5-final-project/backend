//package com.sparta.team5finalproject.service;
//
//
//import com.sparta.team5finalproject.dto.commentDto.CommentResponseDto;
//import com.sparta.team5finalproject.dto.WatchDetailResponseDto;
//import com.sparta.team5finalproject.dto.mainpage.MainPageResponseDto;
//import com.sparta.team5finalproject.model.*;
//import com.sparta.team5finalproject.repository.CodyRepository;
//import com.sparta.team5finalproject.repository.CommentRepository;
//import com.sparta.team5finalproject.repository.CoupleWatchRepository;
//import com.sparta.team5finalproject.repository.WatchRepository;
//import com.sparta.team5finalproject.security.provider.UserDetailsImpl;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RequiredArgsConstructor
//@Service
//public class MainPageService {
//    private final CoupleWatchRepository coupleWatchRepository;
//    private final CodyRepository codyRepository;
//    private final WatchRepository watchRepository;
//    private final CommentRepository commentRepository;
//
//    // 메인페이지 조회
//    public MainPageResponseDto getMainPageWatchList() {
//
//        // 인기 상품
//        MainPageResponseDto mainPageResponseDto = new MainPageResponseDto();
//        List<Watch> tempBestWatchList = watchRepository.findAllByOrderByLikeCountDesc();
//        List<Watch> bestWatchList = new ArrayList<>();
//        for (int i=0; i<5; i++) {
//            bestWatchList.add(tempBestWatchList.get(i));
//        }
//        mainPageResponseDto.setBestList(bestWatchList);
//
//        // 커플 상품
//        List<Watch> tempCoupleWatchList = watchRepository.findAllByOrderByLikeCountDesc();
//        List<Watch> coupleWatchList = new ArrayList<>();
//        for (int j=0; j<5; j++) {
//            coupleWatchList.add(tempCoupleWatchList.get(j));
//        }
//        mainPageResponseDto.setCoupleList(coupleWatchList);
//
//        // 코디글
//        List<Cody> tempCodyPostList = codyRepository.findAllByOrderByIdDesc();
//        List<Cody> codyPostList = new ArrayList<>();
//        for (int l=0; l<5; l++) {
//            codyPostList.add(tempCodyPostList.get(l));
//        }
//        mainPageResponseDto.setCodyList(codyPostList);
//
//        return mainPageResponseDto;
//    }
//
//
//    //카테고리별 페이지 조회
//    public MainPageResponseDto getCategoryWatch() {
//        MainPageResponseDto mainPageResponseDto = new MainPageResponseDto(); //그릇
//        //bestWatch는 성원형님의 쿠팡 워치로 바꿔야 됨. -> 바꿈.
//        List<Watch> digitalCategoryWatch = watchRepository.findAllByWatchCategory(WatchCategory.DIGITAL);
//        List<Watch> coupleCategoryWatch = coupleWatchRepository.findAllByWatchCategory(WatchCategory.COUPLE);
//        mainPageResponseDto.setBestList(digitalCategoryWatch);
//        mainPageResponseDto.setCoupleList(coupleCategoryWatch);
//        return mainPageResponseDto;
//    }
//
//
//    // 시계상세 페이지 조회
//    public WatchDetailResponseDto readDetailWatch(Long watchId, UserDetailsImpl userDetails) {
//        Watch watch = watchRepository.findById(watchId).orElseThrow(
//                () -> new NullPointerException("존재하지 않는 시계입니다."));
//
//        List<WatchComment> watchCommentList = commentRepository.findAllByWatchOrderByCreatedAtAsc(watch);
//        List<CommentResponseDto> commentResponseDtoList= new ArrayList<>();
//        for(Comment oneComment : watchCommentList) {
//            commentResponseDtoList.add(CommentResponseDto.builder()
//                    .commentId(oneComment.getId())
//                    .commentUser(userDetails.getUsername())
//                    .commentContent(oneComment.getCommentContent())
//                    .createdAt(oneComment.getCreatedAt())
//                    .build());
//        }
//        WatchDetailResponseDto watchDetailResponseDto = new WatchDetailResponseDto();
//        watchDetailResponseDto.setWatchId(watch.getWatchId());
//        watchDetailResponseDto.setWatchImage(watch.getWatchImageUrl());
//        watchDetailResponseDto.setWatchBrand(watch.getWatchBrand());
//        watchDetailResponseDto.setLowestPrice(watch.getLowestPrice());
//        watchDetailResponseDto.setLikeCount(String.valueOf(watch.getLikeCount()));
//        watchDetailResponseDto.setCommentResponseDtoList(commentResponseDtoList);
//        return watchDetailResponseDto;
//
//    }
//
//
//}
