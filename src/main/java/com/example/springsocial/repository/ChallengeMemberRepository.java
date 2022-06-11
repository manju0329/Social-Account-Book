package com.example.springsocial.repository;

import com.example.springsocial.model.challenge.ChallengeMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChallengeMemberRepository extends JpaRepository<ChallengeMember, Long> {

    List<ChallengeMember> findByUser_id(Long id);

    List<ChallengeMember> findByChl_id(Long chl_id);
}
