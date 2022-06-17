package com.example.springsocial.repository;

import com.example.springsocial.model.user.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FriendRepository extends JpaRepository<Friend, Long> {

    // 유저ID를 통해 친구 관계 이미 존재하는지 찾기
    Optional<Friend> findByUsertoAndUserfrom(Long user_from, Long user_to);
}
