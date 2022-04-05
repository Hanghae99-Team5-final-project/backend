package com.sparta.team5finalproject.dto.pageDto;


import com.sparta.team5finalproject.model.Cody;
import com.sparta.team5finalproject.model.Watch;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class MainPageResponseDto {
    // 메인페이지 인기상품 리스트
    private List<Watch> bestList;
    // 커플 시계 리스트
    private List<Watch> coupleList;
    // 코디글 리스트
    private List<CodyListResponseDto> codyList;

}