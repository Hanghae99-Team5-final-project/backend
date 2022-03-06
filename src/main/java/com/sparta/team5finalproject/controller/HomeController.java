//package com.sparta.team5finalproject.controller;
//
//
//import com.sparta.team5finalproject.model.Comment;
//import com.sparta.team5finalproject.model.UserRoleEnum;
//import com.sparta.team5finalproject.security.UserDetailsImpl;
//import com.sparta.team5finalproject.service.FolderService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import java.util.List;
//
//@Controller
//public class HomeController {
//
//    private final FolderService folderService;
//
//    @Autowired
//    public HomeController(FolderService folderService) {
//        this.folderService = folderService;
//    }
//
//    @GetMapping("/")
//    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        model.addAttribute("username", userDetails.getUsername());
//
//        if (userDetails.getUser().getRole() == UserRoleEnum.ADMIN) {
//            model.addAttribute("admin_role", true);
//        }
//
//        List<Comment> folderList = folderService.getFolders(userDetails.getUser());
//        model.addAttribute("folders", folderList);
//
//        return "index";
//    }
//}