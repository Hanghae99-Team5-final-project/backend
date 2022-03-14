package com.sparta.crawling.controller;

import com.sparta.crawling.model.Watch;
import com.sparta.crawling.repository.WatchRepository;
import com.sparta.crawling.service.WatchService;
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
