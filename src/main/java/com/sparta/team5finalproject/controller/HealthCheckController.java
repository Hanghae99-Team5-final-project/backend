package com.sparta.team5finalproject.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    // 무중단 배포를 위한 Nginx health check
    @GetMapping("/health")
    public String checkHealth() {
        return "healthy"; }

}
