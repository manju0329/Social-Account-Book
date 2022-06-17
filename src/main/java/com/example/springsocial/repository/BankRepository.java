package com.example.springsocial.repository;

import com.example.springsocial.model.bank.BankPosts;
import com.example.springsocial.payload.BankResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BankRepository extends JpaRepository<BankPosts, Long> {

    // 유저ID로 전체 가계부 내역 조회
    List<BankPosts> findByUserid(Long user_id);

    // 유저ID와 카테고리ID로 챌린지 계산용 내역 조회
    List<BankPosts> findByUseridAndCategoryid(Long user_id, Long cat_id);

    // 단일 가계부 내역 조회
    Optional<BankPosts> findByUseridAndBankid(Long user_id, Long bank_id);
}
