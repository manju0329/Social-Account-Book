package com.example.springsocial.service.challenge;

import com.example.springsocial.model.challenge.Challenge;
import com.example.springsocial.model.posts.Posts;
import com.example.springsocial.payload.*;
import com.example.springsocial.repository.ChallengeMemberRepository;
import com.example.springsocial.repository.ChallengeRepository;
import com.example.springsocial.security.CurrentUser;
import com.example.springsocial.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChallengeService {

    private final ChallengeRepository challengeRepository;
    private final ChallengeMemberRepository challengeMemberRepository;
    private final ChlMemSaveRequestDto chlMemSaveRequestDto;

    public Long getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userPrincipal.getId();
    }

    @Transactional
    //챌린지 등록
    public Long save(ChlSaveRequestDto requestDto, @CurrentUser UserPrincipal userPrincipal){

        Long chl_id = challengeRepository.save(requestDto.toEntity()).getChl_id();
        Long user_id = userPrincipal.getId();
        chlMemSaveRequestDto.setUser_id(user_id);
        chlMemSaveRequestDto.setChl_id(chl_id);
        challengeMemberRepository.save(chlMemSaveRequestDto.toEntity()); // 현재 유저id와 챌린지 id 저장

        return chl_id;
    }



    @Transactional
    //챌린지 상세조회(특정 챌린지의 세부내역 출력) -> 조회할때마다 멤버들의 횟수 가져오기
    public ChlResponseDto findByID(Long id) {
        Challenge entity = challengeRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("해당 번호의 챌린지가 없습니다. id= " + id));

        return new ChlResponseDto(entity);
    }
}
