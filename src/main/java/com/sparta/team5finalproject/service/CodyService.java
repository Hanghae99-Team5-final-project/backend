package com.sparta.team5finalproject.service;

import com.sparta.team5finalproject.dto.codyDto.CodyRequestDto;
import com.sparta.team5finalproject.dto.codyDto.CodyResponseDto;
import com.sparta.team5finalproject.dto.commentDto.CommentResponseDto;

import com.sparta.team5finalproject.model.Cody;
import com.sparta.team5finalproject.model.CodyComment;
import com.sparta.team5finalproject.model.Comment;
import com.sparta.team5finalproject.model.User;
import com.sparta.team5finalproject.repository.CodyRepository;
import com.sparta.team5finalproject.repository.CommentRepository;

import com.sparta.team5finalproject.security.provider.UserDetailsImpl;
import com.sparta.team5finalproject.util.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Service
public class CodyService {

    private Logger logger = LoggerFactory.getLogger(CodyService.class);

    private final CodyRepository codyRepository;
    private final S3Uploader s3Uploader;
    private final CommentRepository commentRepository;
    private final String imageDirName = "cody";   // S3 폴더 경로

    //코디 글 생성
    @Transactional
    public CodyResponseDto createCody(CodyRequestDto codyRequestDto, UserDetailsImpl userDetails, MultipartFile multipartFile) throws IOException {
        // S3 이미지 업로드
        if (userDetails != null) {
            // 이미지가 없으면 빈배열로 및 이미지가 있으면 이미지 업로드
            String imgUrl = "";
            if (multipartFile != null) {
                imgUrl = s3Uploader.upload(multipartFile, imageDirName);
            }
            Cody cody = new Cody();
            cody.setCodyTitle(codyRequestDto.getCodyTitle());
            cody.setWatchBrand(codyRequestDto.getWatchBrand());
            cody.setWatchModel(codyRequestDto.getWatchModel());
            cody.setCodyContent(codyRequestDto.getCodyContent());
            cody.setImageUrl(imgUrl);
            cody.setStar(codyRequestDto.getStar());
            cody.setUser(userDetails.getUser());

            // DB 저장
            try {
                codyRepository.save(cody);
            } catch (IllegalArgumentException e) {
                // 예외처리, 로깅
                String detailMessage = String.format("코디 DB에 create 실패, Input: %s", cody.getCodyTitle());
                logger.info(detailMessage);
                throw new IllegalArgumentException(detailMessage);
            }

            CodyResponseDto codyResponseDto = new CodyResponseDto();
            codyResponseDto.setCodyId(cody.getId());
            codyResponseDto.setUserId(userDetails.getUser().getId());
            codyResponseDto.setUserName(userDetails.getUser().getUsername());
            codyResponseDto.setCodyTitle(cody.getCodyTitle());
            codyResponseDto.setWatchBrand(cody.getWatchBrand());
            codyResponseDto.setWatchModel(cody.getWatchModel());
            codyResponseDto.setCodyContent(cody.getCodyContent());
            codyResponseDto.setImageUrl(cody.getImageUrl());
            codyResponseDto.setStar(cody.getStar());
            codyResponseDto.setCreatedAt(cody.getCreatedAt());

            return codyResponseDto;

        } else {
            throw new NullPointerException("로그인하지 않았습니다.");
        }

    }

    // 코디 상세 조회
    @Transactional
    public CodyResponseDto readDetailCody(Long codyId) {
        Cody cody = codyRepository.findById(codyId).orElseThrow(
                () -> new NullPointerException("존재하지 않는 글입니다."));

        List<CodyComment> codyCommentList = commentRepository.findAllByCodyOrderByCreatedAtAsc(cody);
        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();
        for (Comment oneComment : codyCommentList) {
            commentResponseDtoList.add(CommentResponseDto.builder()
                    .commentId(oneComment.getId())
                    .commentUser(oneComment.getUser().getUsername())
                    .commentContent(oneComment.getCommentContent())
                    .createdAt(oneComment.getCreatedAt())
                    .build());
        }

        CodyResponseDto codyResponseDto = new CodyResponseDto();
        codyResponseDto.setCodyId(cody.getId());
        codyResponseDto.setUserId(cody.getUser().getId());
        codyResponseDto.setUserName(cody.getUser().getUsername());
        codyResponseDto.setCodyTitle(cody.getCodyTitle());
        codyResponseDto.setWatchBrand(cody.getWatchBrand());
        codyResponseDto.setCodyContent(cody.getCodyContent());
        codyResponseDto.setWatchModel(cody.getWatchModel());
        codyResponseDto.setImageUrl(cody.getImageUrl());
        codyResponseDto.setStar(cody.getStar());
        codyResponseDto.setCreatedAt(cody.getCreatedAt());
        codyResponseDto.setCommentResponseDtoList(commentResponseDtoList);
        return codyResponseDto;
    }


    // 코디 목록 조회
    @Transactional
    public List<CodyResponseDto> getIntCody() {
        List<Cody> codyList = codyRepository.findAllByOrderByCreatedAtDesc();
        List<CodyResponseDto> codyResponseDtoList = new ArrayList<>();
        for (Cody oneCody : codyList) {
            List<CodyComment> commentList = commentRepository.findAllByCodyOrderByCreatedAtAsc(oneCody);
            List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();
            for (Comment oneComment : commentList) {
                commentResponseDtoList.add(CommentResponseDto.builder()
                        .commentId(oneComment.getId())
                        .commentUser(oneComment.getUser().getUsername())
                        .commentContent(oneComment.getCommentContent())
                        .createdAt(oneComment.getCreatedAt())
                        .build());
            }
            codyResponseDtoList.add(CodyResponseDto.builder()
                    .codyId(oneCody.getId())
                    .userName(oneCody.getUser().getUsername())
                    .userId(oneCody.getUser().getId())
                    .codyTitle(oneCody.getCodyTitle())
                    .watchBrand(oneCody.getWatchBrand())
                    .watchModel(oneCody.getWatchModel())
                    .codyContent(oneCody.getCodyContent())
                    .imageUrl(oneCody.getImageUrl())
                    .star(oneCody.getStar())
                    .commentResponseDtoList(commentResponseDtoList)
                    .createdAt(oneCody.getCreatedAt())
                    .build());
        }
        return codyResponseDtoList;
    }


    //코디 상세글 수정
    @Transactional
    public CodyResponseDto updateCody(Long codyId, User user, CodyRequestDto codyRequestDto, MultipartFile multipartFile) throws IOException {
        Cody cody = codyRepository.findById(codyId).orElseThrow(
                () -> new NullPointerException("해당 코디글이 존재하지 않습니다."));

        if (!user.getId().equals(cody.getUser().getId())) {
            throw new IllegalArgumentException("해당 코디글의 작성자만 수정 가능합니다.");
        }

        // S3 이미지 수정
        String imgUrl = cody.getImageUrl();
        if (multipartFile != null) {
            String source = URLDecoder.decode(cody.getImageUrl().replace("https://myspringwatch.s3.ap-northeast-2.amazonaws.com/cody/", ""), "UTF-8");
            // 코디글에서 등록했던 imgUrl을 지웠다면 S3에서 삭제
            if (imgUrl.equals(null)) {
                s3Uploader.deleteFromS3(source);
            }
            imgUrl = s3Uploader.upload(multipartFile, imageDirName);
        }
        cody.update(codyRequestDto);
        cody.setImageUrl(imgUrl);

        try {
            codyRepository.save(cody);
            CodyResponseDto codyResponseDto = new CodyResponseDto();
            codyResponseDto.setCodyId(cody.getId());
            codyResponseDto.setUserId(user.getId());
            codyResponseDto.setCodyTitle(cody.getCodyTitle());
            codyResponseDto.setWatchBrand(cody.getWatchBrand());
            codyResponseDto.setWatchModel(cody.getWatchModel());
            codyResponseDto.setCodyContent(cody.getCodyContent());
            codyResponseDto.setImageUrl(cody.getImageUrl());
            codyResponseDto.setStar(cody.getStar());
            codyResponseDto.setCreatedAt(cody.getCreatedAt());

            return codyResponseDto;
        } catch (IllegalArgumentException e) {
            // 예외처리, 로깅
            String detailMessage = String.format("코디 DB에 update 실패, Input: %s", cody.getCodyTitle());
            logger.info(detailMessage);
            throw new IllegalArgumentException(detailMessage);
        }
    }


    // 코디글 삭제
    @Transactional
    public void deleteCody(Long codyId, User user) {
        Cody cody = codyRepository.findById(codyId).orElseThrow(
                () -> new NullPointerException("해당 코디글이 존재하지 않습니다."));

        if (!user.getId().equals(cody.getUser().getId())) {
            throw new IllegalArgumentException("해당 코디글의 작성자만 삭제 가능합니다.");
        }

        // DB 삭제
        try {
            codyRepository.deleteById(codyId);
        } catch (IllegalArgumentException e) {
            // 예외처리, 로깅
            String detailMessage = String.format("코디 DB에 delete 실패, Input: %s", cody.getCodyTitle());
            logger.info(detailMessage);
            throw new IllegalArgumentException(detailMessage);
        }
    }

}