package com.example.springsocial.payload;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 글 수정 DTO
@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto {

    private String title;
    private String content;

    @Builder
    public PostsUpdateRequestDto(String title, String content){
        this.title = title;
        this.content = content;
    }
}
