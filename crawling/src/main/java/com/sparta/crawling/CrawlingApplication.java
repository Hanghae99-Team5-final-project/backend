package com.sparta.crawling;

import com.sparta.crawling.service.WatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;


@SpringBootApplication
public class CrawlingApplication {
    public static void main(String[] args) {
        SpringApplication.run(CrawlingApplication.class, args);
    }

}
