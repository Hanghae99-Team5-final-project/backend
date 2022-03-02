package com.sparta.team5finalproject.controller;


import com.sparta.team5finalproject.dto.FolderRequestDto;
import com.sparta.team5finalproject.model.Folder;
import com.sparta.team5finalproject.model.Product;
import com.sparta.team5finalproject.model.User;
import com.sparta.team5finalproject.security.UserDetailsImpl;
import com.sparta.team5finalproject.service.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FolderController {
    private final FolderService folderService;

    @Autowired
    public FolderController(FolderService folderService) {
        this.folderService = folderService;
    }

    @PostMapping("api/folders")
    public List<Folder> addFolders(
            @RequestBody FolderRequestDto folderRequestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        List<String> folderNames = folderRequestDto.getFolderNames();
        User user = userDetails.getUser();

        return folderService.addFolders(folderNames, user);
    }

    // 회원이 등록한 모든 폴더 조회
    @GetMapping("api/folders")
    public List<Folder> getFolders(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return folderService.getFolders(userDetails.getUser());
    }

    // 회원이 등록한 폴더 내 모든 상품 조회
    @GetMapping("api/folders/{folderId}/products")
    public Page<Product> getProductsInFolder(
            @PathVariable Long folderId,
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy,
            @RequestParam boolean isAsc,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        page = page - 1;
        return folderService.getProductsInFolder(
                folderId,
                page,
                size,
                sortBy,
                isAsc,
                userDetails.getUser()
        );
    }
}