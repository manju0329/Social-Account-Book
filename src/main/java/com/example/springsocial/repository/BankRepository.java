package com.example.springsocial.repository;

import com.example.springsocial.model.bank.BankPosts;
import com.example.springsocial.payload.BankResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BankRepository extends JpaRepository<BankPosts, Long> {

    @Query("SELECT b FROM bank_posts b ORDER BY b.id DESC")
    List<BankPosts> findAllDesc();

    List<BankPosts> findByUser_id(Long user_id);

    List<BankPosts> findByUser_idAndCategory_id(Long user_id, Long cat_id);

    Optional<BankPosts> findByUser_idAndBank_id(Long user_id, Long bank_id);
}
