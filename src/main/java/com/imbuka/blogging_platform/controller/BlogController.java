package com.imbuka.blogging_platform.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/blog")
@RequiredArgsConstructor
public class BlogController {

    @GetMapping
    public ResponseEntity<String> helloBlogs() {
        return ResponseEntity.ok("Hello from Blog platform");
    }
}
