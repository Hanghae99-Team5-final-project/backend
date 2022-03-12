package com.sparta.team5finalproject.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CodyRequestDto {
    private Long userId;
    private String codyTitle;
    private String watchBrand;
    private String watchModel;
    private String codyContent;
//    private String imageUrl;
    private String star;
}
