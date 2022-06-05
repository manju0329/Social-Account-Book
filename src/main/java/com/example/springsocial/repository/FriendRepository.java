package com.example.springsocial.repository;

import com.example.springsocial.model.user.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FriendRepository extends JpaRepository<Friend, Long> {

    Optional<Friend> findByUser_toAndUser_from(Long user_from, Long user_to);
}
