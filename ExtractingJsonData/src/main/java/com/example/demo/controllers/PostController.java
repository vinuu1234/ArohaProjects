package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Services.PostService;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
    private PostService postService;

	/*
	 * public PostController(PostService commentService) { this.postService =
	 * commentService; }
	 */

    @PostMapping("/post")
    public ResponseEntity<String> syncComments() {
        postService.fetchAndStorePosts();
        return ResponseEntity.ok("Comments fetched and saved successfully.");
    }
}

