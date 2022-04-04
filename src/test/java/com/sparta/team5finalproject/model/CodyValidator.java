//package com.sparta.team5finalproject.model;
//
//import com.sparta.team5finalproject.dto.CodyRequestDto;
//import com.sparta.team5finalproject.validator.URLValidator;
//import org.springframework.stereotype.Component;
//
//public class CodyValidator {
//    public static void validateCodyInput(CodyRequestDto requestDto, Long userId) {
//        if (userId == null || userId <= 0) {
//            throw new IllegalArgumentException("회원 Id 가 유효하지 않습니다.");
//        }
//
//        if (requestDto.getTitle() == null || requestDto.getTitle().isEmpty()) {
//            throw new IllegalArgumentException("저장할 수 있는 상품명이 없습니다.");
//        }
//
//        if (!URLValidator.isValidUrl(requestDto.getImage())) {
//            throw new IllegalArgumentException("상품 이미지 URL 포맷이 맞지 않습니다.");
//        }
//
//        if (!URLValidator.isValidUrl(requestDto.getLink())) {
//            throw new IllegalArgumentException("상품 최저가 페이지 URL 포맷이 맞지 않습니다.");
//        }
//
//        if (requestDto.getLprice() <= 0) {
//            throw new IllegalArgumentException("상품 최저가가 0 이하입니다.");
//        }
//    }
//}
