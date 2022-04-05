package com.sparta.team5finalproject.controller;

import com.sparta.team5finalproject.dto.codyDto.CodyRequestDto;
import com.sparta.team5finalproject.dto.codyDto.CodyResponseDto;
import com.sparta.team5finalproject.security.provider.UserDetailsImpl;
import com.sparta.team5finalproject.service.CodyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController // JSON으로 데이터를 주고받음을 선언합니다.
public class CodyController {
    private final CodyService codyService;

    // 신규 코디 글 등록
    @PostMapping("/api/cody")
    public CodyResponseDto createCody(@ModelAttribute CodyRequestDto codyRequestDto,
                           @AuthenticationPrincipal UserDetailsImpl userDetails,
                           @RequestPart(value = "multipartFile", required = false) MultipartFile multipartFile) throws IOException {
       return codyService.createCody(codyRequestDto, userDetails, multipartFile);
    }


    // 코디 상세게시글 조회
    @GetMapping("/api/cody/detail/{codyId}")
    public CodyResponseDto readDetailCody(@PathVariable Long codyId) {
        return codyService.readDetailCody(codyId);
    }


    // 시계 코디 게시글 수정 성원
    @PutMapping("/api/cody/{codyId}")
    public CodyResponseDto updateCody(@PathVariable Long codyId,
                           @AuthenticationPrincipal UserDetailsImpl userDetails,
                           @ModelAttribute CodyRequestDto codyRequestDto,
                           @RequestPart(value = "multipartFile", required = false) MultipartFile multipartFile) throws IOException {
        return codyService.updateCody(codyId, userDetails.getUser(), codyRequestDto, multipartFile);
    }


    // 코디 목록 불러오기
    @GetMapping("/api/cody")
    public List<CodyResponseDto> getIntCody() {
        return codyService.getIntCody();
    }


    // 코디글 삭제
    @DeleteMapping("/api/cody/{codyId}")
    public void deleteCody(@PathVariable Long codyId,
                           @AuthenticationPrincipal UserDetailsImpl userDetails) {
        codyService.deleteCody(codyId, userDetails.getUser());
    }

}