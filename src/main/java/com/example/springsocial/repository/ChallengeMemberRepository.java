package com.example.springsocial.repository;

import com.example.springsocial.model.challenge.ChallengeMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeMemberRepository extends JpaRepository<ChallengeMember, Long> {

}
