package com.example.springsocial.payload;

import com.example.springsocial.model.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 글 저장 DTO
@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;
    @Builder
    public PostsSaveRequestDto(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity(){ // entity 클래스 사용 x -> DB Layer 와 View Layer(DTO - controller) 분리
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
