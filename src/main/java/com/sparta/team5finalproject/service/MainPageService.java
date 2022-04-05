package com.sparta.team5finalproject.service;


import com.sparta.team5finalproject.dto.pageDto.CategoryPageResponesDto;
import com.sparta.team5finalproject.dto.pageDto.CodyListResponseDto;
import com.sparta.team5finalproject.dto.pageDto.MainPageResponseDto;
import com.sparta.team5finalproject.model.Cody;
import com.sparta.team5finalproject.model.Watch;
import com.sparta.team5finalproject.model.WatchCategory;
import com.sparta.team5finalproject.repository.CodyRepository;
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

    // 메인페이지 조회
    public MainPageResponseDto getMainPageWatchList() {

        // 인기 4개의 상품들(찜 기준)
        MainPageResponseDto mainPageResponseDto = new MainPageResponseDto();
        List<Watch> bestWatchList = watchRepository.findTop4ByWatchCategoryOrderByLikeCountDesc(WatchCategory.DIGITAL);

        // 커플 인기 4개의 상품들(찜 기준)
        List<Watch> bestCoupleWatchList = watchRepository.findTop4ByWatchCategoryOrderByLikeCountDesc(WatchCategory.COUPLE);

        // 메인페이지에 보일 코디 글 5개(정렬)
        List<Cody> bestCodyList = codyRepository.findTop5ByOrderByIdDesc();
        List<CodyListResponseDto> codyListResponseDtos = new ArrayList<>();
        for (int i = 0; i < bestCodyList.size(); i++) {
            CodyListResponseDto codyListResponseDto = new CodyListResponseDto();
            codyListResponseDto.setCodyId(bestCodyList.get(i).getId());
            codyListResponseDto.setCodyTitle(bestCodyList.get(i).getCodyTitle());
            codyListResponseDto.setCodyContent(bestCodyList.get(i).getCodyContent());
            codyListResponseDto.setWatchModel(bestCodyList.get(i).getWatchModel());
            codyListResponseDto.setWatchBrand(bestCodyList.get(i).getWatchBrand());
            codyListResponseDto.setStar(bestCodyList.get(i).getStar());
            codyListResponseDto.setImageUrl(bestCodyList.get(i).getImageUrl());
            codyListResponseDtos.add(codyListResponseDto);
        }

        mainPageResponseDto.setBestList(bestWatchList);
        mainPageResponseDto.setCoupleList(bestCoupleWatchList);
        mainPageResponseDto.setCodyList(codyListResponseDtos);

        return mainPageResponseDto;
    }


    //카테고리별 페이지 조회
    public CategoryPageResponesDto getCategoryWatch() {
        CategoryPageResponesDto categoryPageResponesDto = new CategoryPageResponesDto(); //그릇
        List<Watch> digitalCategoryWatch = watchRepository.findAllByWatchCategory(WatchCategory.DIGITAL);
        List<Watch> coupleCategoryWatch = watchRepository.findAllByWatchCategory(WatchCategory.COUPLE);
        categoryPageResponesDto.setDigitalList(digitalCategoryWatch);
        categoryPageResponesDto.setCoupleList(coupleCategoryWatch);
        return categoryPageResponesDto;
    }

}
