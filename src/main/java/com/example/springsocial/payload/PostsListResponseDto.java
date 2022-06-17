package com.example.springsocial.payload;

import com.example.springsocial.model.posts.Posts;
import lombok.Getter;

import java.time.LocalDateTime;

// 전체 글 목록 조회 DTO
@Getter
public class PostsListResponseDto {
    private Long id;
    private String title;
    private String author;
    private LocalDateTime modifiedDate;

    public PostsListResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.modifiedDate = entity.getModifiedDate();
    }
}
