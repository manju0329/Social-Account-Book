package com.example.springsocial.payload;

import com.example.springsocial.model.posts.Posts;
import lombok.Getter;

// 개별 글 조회 DTO
@Getter
public class PostsResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
