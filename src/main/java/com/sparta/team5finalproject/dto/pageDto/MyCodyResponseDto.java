package com.sparta.team5finalproject.dto.pageDto;

import com.sparta.team5finalproject.model.Cody;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
public class MyCodyResponseDto {
    private Long codyId;
    private String codyTitle;
    private String imageUrl;
}


