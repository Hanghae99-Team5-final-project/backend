package com.sparta.team5finalproject.service;


import com.sparta.team5finalproject.dto.CodyRequestDto;
import com.sparta.team5finalproject.dto.CodyResponseDto;
import com.sparta.team5finalproject.dto.CommentResopnseDto;

import com.sparta.team5finalproject.model.Cody;
import com.sparta.team5finalproject.model.CodyComment;
import com.sparta.team5finalproject.model.Comment;
import com.sparta.team5finalproject.model.User;
import com.sparta.team5finalproject.repository.CodyRepository;
import com.sparta.team5finalproject.repository.CommentRepository;

import com.sparta.team5finalproject.security.UserDetailsImpl;
import com.sparta.team5finalproject.util.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.awt.*;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Service
public class CodyService {
    private final CodyRepository codyRepository;
    private final S3Uploader s3Uploader;
    private final CommentRepository commentRepository;

    private final String imageDirName = "cody";   // S3 폴더 경로

//    @Autowired
//    public CodyService(CodyRepository codyRepository, CommentRepository commentRepository, ) {
//        this.codyRepository = codyRepository;
//    }

    //코디 글 생성
    @Transactional
    public void createCody(CodyRequestDto codyRequestDto, User user, MultipartFile multipartFile) throws IOException {
        System.out.println("코디서비스 코디리퀘스트DTO의타이틀="+codyRequestDto.getCodyTitle());

        if (user != null) {
            System.out.println("44444444444444444444444");
            String imgUrl = "";
            if (multipartFile.getSize() != 0) {
                imgUrl = s3Uploader.upload(multipartFile, imageDirName);
            }

            System.out.println("4444444444444444422222222222222");

            // 요청한 정보로 코디 객체 생성
            Cody cody = new Cody();
            cody.setCodyTitle(codyRequestDto.getCodyTitle());
            cody.setWatchBrand(codyRequestDto.getWatchBrand());
            cody.setWatchModel(codyRequestDto.getWatchModel());
            cody.setCodyContent(codyRequestDto.getCodyContent());
            cody.setImageUrl(imgUrl);
            cody.setStar(codyRequestDto.getStar());
            cody.setUser(user);

            System.out.println("55555555555555555555555555="+cody.getCodyTitle());


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

        List<CodyComment> codyCommentList = commentRepository.findAllByCodyOrderByCreatedAtAsc(cody);
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



    // 코디 목록 조회
    @Transactional
    public List<CodyResponseDto> getIntCody(Pageable pageable, User user) {

        // 태그(관심사명), page, size, 내림차순으로 페이징한 게시글 리스트
        List<Cody> codyList = codyRepository.findAllByOrderByCreatedAtDesc(pageable).getContent();

        // 반환할 게시글 리스트 설정
        List<CodyResponseDto> codyResponseDtoList = new ArrayList<>();
        for (Cody oneCody : codyList) {
            // 코디에 달린 댓글 리스트 (작성일자 오름차순)
            List<CodyComment> commentList = commentRepository.findAllByCodyOrderByCreatedAtAsc(oneCody);
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




    // 코디 상세글 수정
    @Transactional
    public void updateCody(Long codyId, User user, CodyRequestDto codyRequestDto) {
        // 코디 상세글 조회
        Cody cody = codyRepository.findById(codyId).orElseThrow(
                () -> new NullPointerException("해당 코디글이 존재하지 않습니다.")
        );

        if (!user.getId().equals(cody.getUser().getId())) {
            throw new IllegalArgumentException("해당 코디글의 작성자만 수정 가능합니다.");
        }

        // 해당 게시글 객체 정보 업데이트
        cody.update(codyRequestDto);

        // DB 저장
        codyRepository.save(cody);
    }




    // 코디글 삭제
    @Transactional
    public void deleteCody(Long codyId, User user) {
        // 코디글 조회
        Cody cody = codyRepository.findById(codyId).orElseThrow(
                () -> new NullPointerException("해당 게시글이 존재하지 않습니다.")
        );

        if (!user.getId().equals(cody.getUser().getId())) {
            throw new IllegalArgumentException("해당 게시글의 작성자만 삭제 가능합니다.");
        }

        // DB 삭제
        codyRepository.deleteById(codyId);
    }

}