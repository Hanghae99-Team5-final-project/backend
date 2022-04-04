package com.sparta.team5finalproject.controller;

import com.sparta.team5finalproject.dto.CodyRequestDto;
import com.sparta.team5finalproject.dto.CodyResponseDto;
import com.sparta.team5finalproject.model.Cody;
import com.sparta.team5finalproject.model.User;
import com.sparta.team5finalproject.model.UserRoleEnum;
import com.sparta.team5finalproject.repository.CodyRepository;
import com.sparta.team5finalproject.repository.UserRepository;
import com.sparta.team5finalproject.security.provider.UserDetailsImpl;
import com.sparta.team5finalproject.service.CodyService;

import jdk.nashorn.internal.runtime.options.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController // JSON으로 데이터를 주고받음을 선언합니다.


public class CodyController {
    private final CodyService codyService;

    // 신규 코디 글 등록
    @PostMapping("/api/cody")
    public void createCody(@ModelAttribute CodyRequestDto codyRequestDto,
                           @AuthenticationPrincipal UserDetailsImpl userDetails,
                           @RequestPart(value = "multipartFile", required = false) MultipartFile multipartFile) throws IOException {
        System.out.println("코디컨트롤의유저="+userDetails.getUser().getUsername());
        System.out.println("코디컨트롤 코디리퀘스트DTO의타이틀="+codyRequestDto.getCodyTitle());
        codyService.createCody(codyRequestDto, userDetails, multipartFile);
    }


//    // 코디 상세게시글 조회
    @GetMapping("/api/cody/detail/{codyId}")
    public CodyResponseDto readDetailCody(@PathVariable Long codyId) {
        return codyService.readDetailCody(codyId);
    }


    // 시계 코디 게시글 수정 성원
    @PutMapping("/api/cody/{codyId}")
    public void updateCody(@PathVariable Long codyId,
                           @AuthenticationPrincipal UserDetailsImpl userDetails,
                           @ModelAttribute CodyRequestDto codyRequestDto,
                           @RequestPart(value = "multipartFile", required = false) MultipartFile multipartFile) throws IOException {
        codyService.updateCody(codyId, userDetails.getUser(), codyRequestDto, multipartFile);
    }


    // 코디 목록 불러오기
    @GetMapping("/api/cody") //localhost:8080/?page==0&size==5
    public List<CodyResponseDto> getIntCody(@RequestParam int page,
                                            @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return codyService.getIntCody(pageable);
    }

//    // 코디글 삭제
    @DeleteMapping("/api/cody/{codyId}")
    public void deleteCody(@PathVariable Long codyId,
                           @AuthenticationPrincipal UserDetailsImpl userDetails) {
        codyService.deleteCody(codyId, userDetails.getUser());
    }





//    // 설정 가격 변경
//    @PutMapping("/api/cody/{id}")
//    public Long updateCody(@PathVariable Long id, @RequestBody CodyMypriceRequestDto codyRequestDto) {
//        Cody cody = codyService.updateCody(id, codyRequestDto);
//
//        // 응답 보내기 (업데이트된 상품 id)
//        return cody.getId();
//    }
//
//    // 로그인한 회원이 등록한 관심 상품 조회
//    @GetMapping("/api/cody")
//    public Page<Cody> getCodys(
//            @RequestParam("page") int page,
//            @RequestParam("size") int size,
//            @RequestParam("sortBy") String sortBy,
//            @RequestParam("isAsc") boolean isAsc,
//            @AuthenticationPrincipal UserDetailsImpl userDetails
//    ) {
//        // 로그인 되어 있는 회원 테이블의 ID
//        Long userId = userDetails.getUser().getId();
//        page = page - 1;
//
//        return codyService.getCodys(userId, page, size, sortBy, isAsc);
//    }
//
//    // (관리자용) 전체 상품 조회
//    @Secured(UserRoleEnum.Authority.ADMIN)
//    @GetMapping("/api/admin/cody")
//    public Page<Cody> getAllCodys(
//            @RequestParam("page") int page,
//            @RequestParam("size") int size,
//            @RequestParam("sortBy") String sortBy,
//            @RequestParam("isAsc") boolean isAsc
//    ) {
//        page = page - 1;
//        return codyService.getAllCodys(page, size, sortBy, isAsc);
//    }
//
//    // 상품에 폴더 추가
//    @PostMapping("/api/cody/{codyId}/folder")
//    public Long addFolder(
//            @PathVariable Long codyId,
//            @RequestParam Long folderId,
//            @AuthenticationPrincipal UserDetailsImpl userDetails
//    ) {
//        User user = userDetails.getUser();
//        Cody cody = codyService.addFolder(codyId, folderId, user);
//        return cody.getId();
//    }
}