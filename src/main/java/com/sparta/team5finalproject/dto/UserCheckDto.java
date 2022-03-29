package com.sparta.team5finalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UserCheckDto {
    private Long userId;
    private String username;
    private String email;
    private boolean check;
}
