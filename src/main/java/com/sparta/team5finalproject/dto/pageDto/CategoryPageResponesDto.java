package com.sparta.team5finalproject.dto.pageDto;

import com.sparta.team5finalproject.model.Watch;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
public class CategoryPageResponesDto {
    // 커플시계 리스트
    private List<Watch> coupleList;
    // 아날로그 시계 리스트
    private List<Watch> digitalList;

}
