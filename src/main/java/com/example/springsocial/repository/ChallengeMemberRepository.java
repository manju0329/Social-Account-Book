package com.example.springsocial.repository;

import com.example.springsocial.model.challenge.ChallengeMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChallengeMemberRepository extends JpaRepository<ChallengeMember, Long> {

    // 유저ID를 통한 챌린지 멤버 찾기
    List<ChallengeMember> findByUserid(Long id);

    // 챌린지ID를 통한 챌린지 멤버 찾기
    List<ChallengeMember> findByChlid(Long chl_id);
}
