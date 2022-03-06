package com.sparta.team5finalproject.service;


import com.sparta.team5finalproject.dto.CodyRequestDto;
import com.sparta.team5finalproject.model.Cody;
import com.sparta.team5finalproject.model.Comment;
import com.sparta.team5finalproject.model.User;
//import com.sparta.team5finalproject.repository.FolderRepository;
import com.sparta.team5finalproject.repository.CodyRepository;
import com.sparta.team5finalproject.util.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class CodyService {
    private final CodyRepository codyRepository;

    private final S3Uploader s3Uploader;

    private final String imageDirName = "cody";   // S3 폴더 경로

//    @Autowired
//    public CodyService(CodyRepository codyRepository){
//        this.codyRepository = codyRepository;
//    }

    @Transactional
    public void createCody(CodyRequestDto codyRequestDto, User user, MultipartFile multipartFile) {
        ValidChecker.login

        if (user != null) {
            // 요청한 정보로 코디 객체 생성
            Cody cody = new Cody();
            cody.setCodyTitle(codyRequestDto.getCodyTitle());
            cody.setWatchBrand(codyRequestDto.getWatchBrand());
            cody.setWatchModel(codyRequestDto.getWatchModel());
            cody.setCodyContent(codyRequestDto.getCodyContent());
            cody.setImageUrl(codyRequestDto.getImageUrl());
            cody.setStar(codyRequestDto.getStar());
            cody.setUser(user);

            // DB 저장
            codyRepository.save(cody);

            // 이미지 리스트
            List<Image> imageList = new ArrayList<>();
            for (int i = 0; i < multipartFile.size(); i++) {
                String imgUrl = "";

                // 이미지 첨부 있으면 URL 에 S3에 업로드된 파일 url 저장
                if (multipartFile.get(i).getSize() != 0) {
                    imgUrl = s3Uploader.upload(multipartFile.get(i), imageDirName);
                }
                imageList.add(Image.builder()
                        .imageLink(imgUrl)
                        .post(post)
                        .build());
            }

            // DB 저장
            imageRepository.saveAll(imageList);
        } else {
            throw new NullPointerException("로그인하지 않았습니다.");
        }

    }
    //밈글밈글 게시글 작성 부분
    // 글 작성할려는 유저 로그인 여부 확인
        ValidChecker.loginCheck(userDetails);
    // 1. Request 로 넘어온 데이터 유효성 검사(게시글 제목, 게시글 내용)
    String boardTitle = boardUploadRequestDto.getTitle();
    String boardContent = boardUploadRequestDto.getContent();
        if (boardTitle.isEmpty()) {
        throw new IllegalArgumentException(TITLE_IS_EMPTY);
    }
        if (boardContent.isEmpty()) {
        throw new IllegalArgumentException(CONTENT_IS_EMPTY);
    }

    // 2. Request 로 넘어온 카테고리 네임 DB 에서 조회
    BoardCategory boardCategory = getSafeBoardCategory(categoryName);
    // 3. multipartFile 로 넘어온 이미지 데이터 null 체크 => null 이 아니면 S3 버킷에 저장
    String imageUrl = "";
        if (!multipartFile.isEmpty()) {
        imageUrl = s3Uploader.upload(multipartFile, S3dirName);
    }

    // 4. 게시글 데이터 DB에 저장
    Board board = Board.builder()
            .title(boardTitle)                                    // 제목
            .content(boardContent)                                // 내용
            .boardCategory(boardCategory)                         // 카테고리
            .user(jwtAuthenticateProcessor.getUser(userDetails))  // 유저
            .thumbNail(imageUrl)                                  // 이미지 URL
            .enabled(true)                                        // 게시글 삭제 여부
            .build();
        boardRepository.save(board);


    // 5. 작성한 게시글에 맞는 이미지 저장
    BoardImage boardImage = BoardImage.builder()
            .board(board)
            .imageUrl(imageUrl)
            .build();
        boardImageRepository.save(boardImage);

    // 6. 저장한 데이터 기반으로 Response(게시글번호, 제목, 내용, 카테고리, 이미지 URL, 생성날짜)
        return BoardUploadResponseDto.builder()
                .boardId(board.getBoardId())                                            // 게시글아이디
            .title(board.getTitle())                                                // 제목
            .content(board.getContent())                                            // 내용
            .category(board.getBoardCategory().getCategoryName())                   // 카테고리
            .thumbNail(board.getThumbNail())                                        // 이미지 URL
            .createdAt(board.getCreatedAt() == null ? null : board.getCreatedAt())  // 게시글 생성 날짜
            .build();
}
    //endregion


























    //















    public Cody updateCody(Long id, CodyMypriceRequestDto requestDto) {
        int myprice = requestDto.getMyprice();
        if (myprice < MIN_MY_PRICE) {
            throw new IllegalArgumentException("유효하지 않은 관심 가격입니다. 최소 " + MIN_MY_PRICE + " 원 이상으로 설정해 주세요.");
        }

        Cody product = productRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("해당 아이디가 존재하지 않습니다."));

        product.setMyprice(myprice);
        productRepository.save(product);

        return product;
    }

    // 회원 ID 로 등록된 상품 조회
    public Page<Cody> getCodys(Long userId, int page, int size, String sortBy, boolean isAsc) {
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        return productRepository.findAllByUserId(userId, pageable);
    }

    // (관리자용) 상품 전체 조회
    public Page<Cody> getAllCodys(int page, int size, String sortBy, boolean isAsc) {
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        return productRepository.findAll(pageable);
    }

    @Transactional
    public Cody addFolder(Long productId, Long folderId, User user) {
        // 1) 상품을 조회합니다.
        Cody product = productRepository.findById(productId)
                .orElseThrow(() -> new NullPointerException("해당 상품 아이디가 존재하지 않습니다."));

        // 2) 관심상품을 조회합니다.
        Comment folder = folderRepository.findById(folderId)
                .orElseThrow(() -> new NullPointerException("해당 폴더 아이디가 존재하지 않습니다."));

        // 3) 조회한 폴더와 관심상품이 모두 로그인한 회원의 소유인지 확인합니다.
        Long loginUserId = user.getId();
        if (!product.getUserId().equals(loginUserId) || !folder.getUser().getId().equals(loginUserId)) {
            throw new IllegalArgumentException("회원님의 관심상품이 아니거나, 회원님의 폴더가 아닙니다~^^");
        }

        // 4) 상품에 폴더를 추가합니다.
        product.addFolder(folder);

        return product;
    }
}