package com.sparta.team5finalproject.dto.pageDto;

import com.sparta.team5finalproject.model.Watch;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
public class CategoryPageResponesDto {
    private List<Watch> coupleList;
    private List<Watch> digitalList;
}
