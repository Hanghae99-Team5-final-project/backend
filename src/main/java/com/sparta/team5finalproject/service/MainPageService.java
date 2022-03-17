package com.sparta.team5finalproject.service;


import com.sparta.team5finalproject.dto.pageDto.CategoryPageResponesDto;
import com.sparta.team5finalproject.dto.pageDto.MainPageResponseDto;
import com.sparta.team5finalproject.model.*;
import com.sparta.team5finalproject.repository.CodyRepository;
import com.sparta.team5finalproject.repository.CommentRepository;
import com.sparta.team5finalproject.repository.WatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MainPageService {
    private final CodyRepository codyRepository;
    private final WatchRepository watchRepository;
    private final CommentRepository commentRepository;

    // 메인페이지 조회
    public MainPageResponseDto getMainPageWatchList() {

        // 인기 상품
        MainPageResponseDto mainPageResponseDto = new MainPageResponseDto();
        List<Watch> bestWatchList = watchRepository.findTop5ByWatchCategoryOrderByLikeCountDesc(WatchCategory.DIGITAL);

//        List<Watch> TopWatchList = new ArrayList<>();
//        for (Watch digital : bestWatchList) {
//            String category = digital.getWatchCategory().getCategory();
//
//            if (category == "digital") {
//                TopWatchList.add(digital);
//            }

//            if (TopWatchList.size() == 5) {
//                break;
//            }
//       }


        // 커플 인기 상품
        List<Watch> bestCoupleWatchList = watchRepository.findTop5ByWatchCategoryOrderByLikeCountDesc(WatchCategory.COUPLE);

//        for (Watch couple : bestCoupleWatchList){
//            String category = couple.getWatchCategory().getCategory();
//            System.out.println("카테고리는 잘 찍히십니까? : " + category);

//            if (category == "couple"){
//                    TopCoupleWatchList.add(couple);
//                    System.out.println();
//                }
//
//                if (TopWatchList.size() == 5) {
//                    System.out.println("잘 찍히나 테스트입니다. : "+TopCoupleWatchList);
//                    break;
//            }
//       }

        mainPageResponseDto.setBestList(bestWatchList);
        mainPageResponseDto.setCoupleList(bestCoupleWatchList);


        // 코디글
//        List<Cody> bestCodyList = codyRepository.findTop5ByOrderByIdDesc();
//        mainPageResponseDto.setCodyList(bestCodyList);
//
//        mainPageResponseDto.setCodyList(bestCodyList);
        return mainPageResponseDto;
    }


//    //카테고리별 페이지 조회
    public CategoryPageResponesDto getCategoryWatch() {
        CategoryPageResponesDto categoryPageResponesDto = new CategoryPageResponesDto(); //그릇
        List<Watch> digitalCategoryWatch = watchRepository.findAllByWatchCategory(WatchCategory.DIGITAL);
        List<Watch> coupleCategoryWatch = watchRepository.findAllByWatchCategory(WatchCategory.COUPLE);
        categoryPageResponesDto.setDigitalList(digitalCategoryWatch);
        categoryPageResponesDto.setCoupleList(coupleCategoryWatch);
        return categoryPageResponesDto;
    }

//


}
