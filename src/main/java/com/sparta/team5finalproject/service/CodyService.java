package com.sparta.team5finalproject.service;


import com.sparta.team5finalproject.dto.CodyRequestDto;
import com.sparta.team5finalproject.dto.CodyResponseDto;
import com.sparta.team5finalproject.dto.CommentResopnseDto;
import com.sparta.team5finalproject.dto.ImageResponseDto;
import com.sparta.team5finalproject.model.Cody;
import com.sparta.team5finalproject.model.CodyComment;
import com.sparta.team5finalproject.model.Comment;
import com.sparta.team5finalproject.model.User;
import com.sparta.team5finalproject.model.Image;
//import com.sparta.team5finalproject.repository.FolderRepository;
import com.sparta.team5finalproject.repository.CodyRepository;
import com.sparta.team5finalproject.repository.CommentRepository;
import com.sparta.team5finalproject.repository.ImageRepository;
import com.sparta.team5finalproject.util.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CodyService {
    private final CodyRepository codyRepository;
    private final S3Uploader s3Uploader;
    private final CommentRepository commentRepository;


    private final String imageDirName = "cody";   // S3 폴더 경로

    //코디 글 생성
    @Transactional
    public void createCody(CodyRequestDto codyRequestDto, User user, MultipartFile multipartFile) throws IOException {

        if (user != null) {

            String imgUrl = "";
            if (multipartFile.getSize() != 0) {
                imgUrl = s3Uploader.upload(multipartFile, imageDirName);
            }

            // 요청한 정보로 코디 객체 생성
            Cody cody = new Cody();
            cody.setCodyTitle(codyRequestDto.getCodyTitle());
            cody.setWatchBrand(codyRequestDto.getWatchBrand());
            cody.setWatchModel(codyRequestDto.getWatchModel());
            cody.setCodyContent(codyRequestDto.getCodyContent());
            cody.setImageUrl(imgUrl);
            cody.setStar(codyRequestDto.getStar());
            cody.setUser(user);

            // DB 저장
            codyRepository.save(cody);

            } else {
                throw new NullPointerException("로그인하지 않았습니다.");
            }

    }

    // 코디 상세 조회
    @Transactional
    public CodyResponseDto readDetailCody(Long codyId, User user){
        Cody cody = codyRepository.findById(codyId).orElseThrow(
                () -> new NullPointerException("존재하지 않는 글입니다."));

        List<Comment> codyCommentList = commentRepository.findAllByCodyOrderByCreatedAtAsc(cody);
        List<CommentResopnseDto> commentResopnseDtoList = new ArrayList<>();
        for(Comment oneComment : codyCommentList) {
            commentResopnseDtoList.add(CommentResopnseDto.builder()
                    .commentId(oneComment.getId())
                    .commentUser(user.getUsername())
                    .commentContent(oneComment.getCommentContent())
                    .createdAt(oneComment.getCreatedAt())
                    .build());
        }

        CodyResponseDto codyResponseDto = new CodyResponseDto();
        codyResponseDto.setCodyTitle(cody.getCodyTitle());
        codyResponseDto.setWatchBrand(cody.getWatchBrand());
        codyResponseDto.setCodyContent(cody.getCodyContent());
        codyResponseDto.setWatchModel(cody.getWatchModel());
        codyResponseDto.setImageUrl(cody.getImageUrl());
        codyResponseDto.setStar(cody.getStar());
        codyResponseDto.setCommentResopnseDtoList(commentResopnseDtoList);
        return codyResponseDto;
    }



//////////////////////////////////////////////////////////////////////
    @Transactional
    public List<CodyResponseDto> getIntCody(Pageable pageable, User user) {

        // 태그(관심사명), page, size, 내림차순으로 페이징한 게시글 리스트
        List<Cody> codyList = codyRepository.findAllByOrderByCreatedAtDesc(pageable).getContent();

        // 반환할 게시글 리스트 설정
        List<CodyResponseDto> codyResponseDtoList = new ArrayList<>();
        for (Cody oneCody : codyList) {
            // 코디에 달린 댓글 리스트 (작성일자 오름차순)
            List<Comment> commentList = commentRepository.findAllByCodyOrderByCreatedAtAsc(oneCody);
            // 반환할 댓글 리스트
            List<CommentResopnseDto> commentResopnseDtoList = new ArrayList<>();
            for (Comment oneComment : commentList) {
                commentResopnseDtoList.add(CommentResopnseDto.builder()
                        .commentId(oneComment.getId())
                        .commentUser(user.getUsername())
                        .commentContent(oneComment.getCommentContent())
                        .createdAt(oneComment.getCreatedAt())
                        .build());
            }

            codyResponseDtoList.add(CodyResponseDto.builder()
                    .codyId(oneCody.getId())
//                    .userId(user.getId())
                    .codyTitle(oneCody.getCodyTitle())
                    .watchBrand(oneCody.getWatchBrand())
                    .watchModel(oneCody.getWatchModel())
                    .codyContent(oneCody.getCodyContent())
                    .imageUrl(oneCody.getImageUrl())
                    .star(oneCody.getStar())
                    .commentResopnseDtoList(commentResopnseDtoList)
                    .createdAt(oneCody.getCreatedAt())
                    .build());
        }
        return codyResponseDtoList;
    }
//////////////////////////////////////////////////////////////////////








//    public Cody updateCody(Long id, CodyMypriceRequestDto requestDto) {
//        int myprice = requestDto.getMyprice();
//        if (myprice < MIN_MY_PRICE) {
//            throw new IllegalArgumentException("유효하지 않은 관심 가격입니다. 최소 " + MIN_MY_PRICE + " 원 이상으로 설정해 주세요.");
//        }
//
//        Cody product = productRepository.findById(id)
//                .orElseThrow(() -> new NullPointerException("해당 아이디가 존재하지 않습니다."));
//
//        product.setMyprice(myprice);
//        productRepository.save(product);
//
//        return product;
//    }
//
//    // 회원 ID 로 등록된 상품 조회
//    public Page<Cody> getCodys(Long userId, int page, int size, String sortBy, boolean isAsc) {
//        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
//        Sort sort = Sort.by(direction, sortBy);
//        Pageable pageable = PageRequest.of(page, size, sort);
//
//        return productRepository.findAllByUserId(userId, pageable);
//    }
//
//    // (관리자용) 상품 전체 조회
//    public Page<Cody> getAllCodys(int page, int size, String sortBy, boolean isAsc) {
//        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
//        Sort sort = Sort.by(direction, sortBy);
//        Pageable pageable = PageRequest.of(page, size, sort);
//
//        return productRepository.findAll(pageable);
//    }
//
//    @Transactional
//    public Cody addFolder(Long productId, Long folderId, User user) {
//        // 1) 상품을 조회합니다.
//        Cody product = productRepository.findById(productId)
//                .orElseThrow(() -> new NullPointerException("해당 상품 아이디가 존재하지 않습니다."));
//
//        // 2) 관심상품을 조회합니다.
//        Comment folder = folderRepository.findById(folderId)
//                .orElseThrow(() -> new NullPointerException("해당 폴더 아이디가 존재하지 않습니다."));
//
//        // 3) 조회한 폴더와 관심상품이 모두 로그인한 회원의 소유인지 확인합니다.
//        Long loginUserId = user.getId();
//        if (!product.getUserId().equals(loginUserId) || !folder.getUser().getId().equals(loginUserId)) {
//            throw new IllegalArgumentException("회원님의 관심상품이 아니거나, 회원님의 폴더가 아닙니다~^^");
//        }
//
//        // 4) 상품에 폴더를 추가합니다.
//        product.addFolder(folder);
//
//        return product;
//    }
}