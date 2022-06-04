package com.example.springsocial.repository;

import com.example.springsocial.model.bank.BankPosts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BankRepository extends JpaRepository<BankPosts, Long> {

    @Query("SELECT b FROM bank_posts b ORDER BY b.id DESC")
    List<BankPosts> findAllDesc();
}
