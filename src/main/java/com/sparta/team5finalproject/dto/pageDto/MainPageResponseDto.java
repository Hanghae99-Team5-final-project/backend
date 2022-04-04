package com.sparta.team5finalproject.dto.pageDto;


import com.sparta.team5finalproject.model.Cody;
import com.sparta.team5finalproject.model.Watch;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class MainPageResponseDto {

    private List<Watch> bestList;
    private List<Watch> coupleList;
    private List<CodyListResponseDto> codyList;



}