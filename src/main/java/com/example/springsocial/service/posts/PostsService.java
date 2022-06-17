package com.example.springsocial.service.posts;


import com.example.springsocial.model.posts.Posts;
import com.example.springsocial.payload.PostsListResponseDto;
import com.example.springsocial.payload.PostsResponseDto;
import com.example.springsocial.payload.PostsSaveRequestDto;
import com.example.springsocial.payload.PostsUpdateRequestDto;
import com.example.springsocial.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional // 게시글 저장
    public Long save(PostsSaveRequestDto requestDto)
    {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional // 게시글 수정
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts =  postsRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("해당 게시글이 없습니다. id= " + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    // 게시글ID로 검색
    @Transactional(readOnly = true)
    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("해당 게시글이 없습니다. id= " + id));

        return new PostsResponseDto(entity);
    }

    // 전체 게시글 조회
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
        // posts의 stream을 map을 이용해서 PostsListResponseDto로 변환 -> List 타입으로 반환
    }

    // 특정 게시글 삭제
    @Transactional
    public void delete (Long id){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        postsRepository.delete(posts);
    }
}