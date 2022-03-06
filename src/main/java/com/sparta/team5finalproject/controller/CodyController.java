package com.sparta.team5finalproject.controller;


//import com.sparta.team5finalproject.dto.CodyMypriceRequestDto;
import com.sparta.team5finalproject.dto.CodyRequestDto;
import com.sparta.team5finalproject.model.Cody;
import com.sparta.team5finalproject.model.User;
import com.sparta.team5finalproject.model.UserRoleEnum;
import com.sparta.team5finalproject.repository.CodyRepository;
import com.sparta.team5finalproject.security.UserDetailsImpl;
import com.sparta.team5finalproject.service.CodyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController // JSON으로 데이터를 주고받음을 선언합니다.
public class CodyController {

    private final CodyRepository codyRepository;
    private final CodyService codyService;

    @Autowired
    public CodyController(CodyService codyService, CodyRepository codyRepository) {
        this.codyService = codyService;
        this.codyRepository = codyRepository;
    }

    // 신규 상품 등록
    @PostMapping("/api/cody")
    public void createCody(@RequestBody CodyRequestDto codyRequestDto,
                           @AuthenticationPrincipal UserDetailsImpl userDetails,
                           @RequestPart(value = "multipartFile", required = false) List<MultipartFile> multipartFile) {

        codyService.createCody(codyRequestDto, userDetails.getUser(), multipartFile);
    }


    // 게시글 작성
    @PostMapping("/api/post")
    public void createPost(@AuthenticationPrincipal UserDetailsImpl userDetails,
                           @RequestPart(value = "data") PostRequestDto postRequestDto,
                           @RequestPart(value = "multipartFile", required = false) List<MultipartFile> multipartFile
    ) throws IOException {
        postService.createPost(userDetails.getUser(), postRequestDto, multipartFile);
    }





//    // 설정 가격 변경
//    @PutMapping("/api/cody/{id}")
//    public Long updateCody(@PathVariable Long id, @RequestBody CodyMypriceRequestDto codyRequestDto) {
//        Cody cody = codyService.updateCody(id, codyRequestDto);
//
//        // 응답 보내기 (업데이트된 상품 id)
//        return cody.getId();
//    }

    // 로그인한 회원이 등록한 관심 상품 조회
    @GetMapping("/api/cody")
    public Page<Cody> getCodys(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy,
            @RequestParam("isAsc") boolean isAsc,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        // 로그인 되어 있는 회원 테이블의 ID
        Long userId = userDetails.getUser().getId();
        page = page - 1;

        return codyService.getCodys(userId, page, size, sortBy, isAsc);
    }

    // (관리자용) 전체 상품 조회
    @Secured(UserRoleEnum.Authority.ADMIN)
    @GetMapping("/api/admin/cody")
    public Page<Cody> getAllCodys(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy,
            @RequestParam("isAsc") boolean isAsc
    ) {
        page = page - 1;
        return codyService.getAllCodys(page, size, sortBy, isAsc);
    }

    // 상품에 폴더 추가
    @PostMapping("/api/cody/{codyId}/folder")
    public Long addFolder(
            @PathVariable Long codyId,
            @RequestParam Long folderId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        User user = userDetails.getUser();
        Cody cody = codyService.addFolder(codyId, folderId, user);
        return cody.getId();
    }
}