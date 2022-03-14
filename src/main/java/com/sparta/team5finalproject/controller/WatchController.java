package com.sparta.team5finalproject.controller;

import com.sparta.team5finalproject.model.Watch;
import com.sparta.team5finalproject.repository.WatchRepository;
import com.sparta.team5finalproject.service.WatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class WatchController {
    private final WatchRepository watchRepository;
    private final WatchService watchService;


    @PostMapping("/")
    public void create() throws IOException {
        watchService.total();
    }

}
