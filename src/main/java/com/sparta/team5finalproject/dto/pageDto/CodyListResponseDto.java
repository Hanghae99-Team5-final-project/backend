package com.sparta.team5finalproject.dto.pageDto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CodyListResponseDto {
    private  Long codyId;
    private String codyTitle;
    private String watchBrand;
    private String watchModel;
    private String codyContent;
    private String imageUrl;
    private String star;
}
