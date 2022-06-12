package com.example.springsocial.repository;

import com.example.springsocial.model.challenge.ChallengeMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChallengeMemberRepository extends JpaRepository<ChallengeMember, Long> {

    List<ChallengeMember> findByUserid(Long id);

    List<ChallengeMember> findByChlid(Long chl_id);
}
