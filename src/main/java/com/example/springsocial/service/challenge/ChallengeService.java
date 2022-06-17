package com.example.springsocial.service.challenge;

import com.example.springsocial.model.challenge.Challenge;
import com.example.springsocial.model.challenge.ChallengeMember;
import com.example.springsocial.payload.*;
import com.example.springsocial.repository.BankRepository;
import com.example.springsocial.repository.ChallengeMemberRepository;
import com.example.springsocial.repository.ChallengeRepository;
import com.example.springsocial.security.CurrentUser;
import com.example.springsocial.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChallengeService {

    private final ChallengeRepository challengeRepository;
    private final ChallengeMemberRepository challengeMemberRepository;
    private final BankRepository bankRepository;

    public Long getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userPrincipal.getId();
    }

    @Transactional
    //챌린지 등록
    public Long save(ChlSaveRequestDto requestDto){

        Long chl_id = challengeRepository.save(requestDto.toEntity()).getChlid();

        return chl_id;
    }

    // 챌린지 전체조회(유저의 참여 챌린지 전체 리스트 가져오기) -> 리스트에서는 챌린지id만 출력필요
    @Transactional
    public List<ChlListResponseDto> findAllbyID(Long id){ // 현재 유저id가 진행중인 모든 챌린지 list
        return challengeMemberRepository.findByUserid(id).stream()
                .map(ChlListResponseDto::new)
                .collect(Collectors.toList());

    }

    @Transactional
    // 챌린지 ID를 통한 참여멤버 목록 찾기
    public List<Long> findChlMem(Long chl_id){
        List chl_list = challengeMemberRepository.findByChlid(chl_id);
        List<Long> chl_mem = new ArrayList<Long>();

        for(int i = 0; i < chl_list.size(); i++){
            ChallengeMember challengeMember = (ChallengeMember)chl_list.get(i);
            challengeMember.getUserid();
            chl_mem.add(challengeMember.getUserid());
        }
        
        return chl_mem;
    }


    @Transactional
    //챌린지 상세조회(특정 챌린지의 세부내역 출력) -> 조회할때마다 멤버들의 횟수 가져오기
    // 1. ID를 통한 조회하려는 챌린지 검색
    public Challenge findChl(Long id) {
        Challenge entity = challengeRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("해당 번호의 챌린지가 없습니다. id= " + id));

        return entity;
    }

    @Transactional
    // 2. 챌린지에 설정된 목표카테고리와 목표횟수/금액를 참여멤버의 저장된 가계부에서 가져오기
    public LinkedHashMap<Long, Long> calResult(Challenge chl){
        Long chl_id = chl.getChlid();
        Long chl_cat = chl.getChl_cat(); // 조회할 목표 카테고리

        List member = (List) chl.getChallengeMember(); // 참여 멤버 list
        LinkedHashMap result = new LinkedHashMap<Long, Long>(); // <참여멤버ID, 현재횟수>

        for(int i=0; i < member.size(); i++){
            List chl_result = bankRepository.findByUseridAndCategoryid((Long)member.get(i), chl_cat);
            Long fre_result = Long.valueOf(chl_result.size());
            result.put(member.get(i), fre_result);
        }

        return result;

    }
}
