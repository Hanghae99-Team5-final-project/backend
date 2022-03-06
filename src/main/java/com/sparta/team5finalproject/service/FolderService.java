//package com.sparta.team5finalproject.service;
//
//
//import com.sparta.team5finalproject.model.Comment;
//import com.sparta.team5finalproject.model.User;
//import com.sparta.team5finalproject.repository.FolderRepository;
//import com.sparta.team5finalproject.repository.ProductRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class FolderService {
//
//    private final FolderRepository folderRepository;
//    private final ProductRepository productRepository;
//
//    @Autowired
//    public FolderService(
//            FolderRepository folderRepository,
//            ProductRepository productRepository
//    ) {
//        this.folderRepository = folderRepository;
//        this.productRepository = productRepository;
//    }
//
//    // 로그인한 회원에 폴더들 등록
//    public List<Comment> addFolders(List<String> folderNames, User user) {
//        // 1) 입력으로 들어온 폴더 이름을 기준으로, 회원이 이미 생성한 폴더들을 조회합니다.
//        List<Comment> existFolderList = folderRepository.findAllByUserAndNameIn(user, folderNames);
//
//        List<Comment> folderList = new ArrayList<>();
//        for (String folderName : folderNames) {
//            // 2) 이미 생성한 폴더가 아닌 경우만 폴더 생성
//            if (!isExistFolderName(folderName, existFolderList)) {
//                Comment folder = new Comment(folderName, user);
//                folderList.add(folder);
//            }
//        }
//
//        return folderRepository.saveAll(folderList);
//    }
//
//    // 로그인한 회원이 등록된 모든 폴더 조회
//    public List<Comment> getFolders(User user) {
//        return folderRepository.findAllByUser(user);
//    }
//
//    // 회원 ID 가 소유한 폴더에 저장되어 있는 상품들 조회
//    public Page<Product> getProductsInFolder(
//            Long folderId,
//            int page,
//            int size,
//            String sortBy,
//            boolean isAsc,
//            User user
//    ) {
//        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
//        Sort sort = Sort.by(direction, sortBy);
//        Pageable pageable = PageRequest.of(page, size, sort);
//        Long userId = user.getId();
//        return productRepository.findAllByUserIdAndFolderList_Id(userId, folderId, pageable);
//    }
//
//    private boolean isExistFolderName(String folderName, List<Comment> existFolderList) {
//        // 기존 폴더 리스트에서 folder name 이 있는지?
//        for (Comment existFolder : existFolderList) {
//            if (existFolder.getName().equals(folderName)) {
//                return true;
//            }
//        }
//
//        return false;
//    }
//}