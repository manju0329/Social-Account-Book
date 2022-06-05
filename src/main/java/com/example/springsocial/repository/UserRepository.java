package com.example.springsocial.repository;

import com.example.springsocial.model.user.Friend;
import com.example.springsocial.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email); // email로 중복가입 여부 확인

    Boolean existsByEmail(String email);



}
