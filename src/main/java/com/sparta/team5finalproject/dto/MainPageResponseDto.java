package com.sparta.team5finalproject.dto;


import com.sparta.crawl.model.BestWatch;
import com.sparta.crawl.model.CoupleWatch;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class MainPageResponseDto {

    private List<BestWatch> bestList;
    private List<CoupleWatch> coupleList;
    private List<Cody> codyList;


}
