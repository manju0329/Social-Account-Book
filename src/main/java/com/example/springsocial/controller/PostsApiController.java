package com.example.springsocial.controller;

import com.example.springsocial.model.posts.Posts;
import com.example.springsocial.payload.PostsResponseDto;
import com.example.springsocial.payload.PostsSaveRequestDto;
import com.example.springsocial.payload.PostsUpdateRequestDto;
import com.example.springsocial.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts/write") // 글 저장
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}") // 글 수정(id=글번호)
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts") // 전체 글 조회
    public String posts(Model model){
        model.addAttribute("posts", postsService.findAllDesc());
        return "posts";
    }

    @GetMapping("/api/v1/posts/{id}") // 특정 글 조회
    public PostsResponseDto findByID (@PathVariable Long id){
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}") //글 삭제
    public Long delete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }
}

