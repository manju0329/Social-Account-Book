package com.example.springsocial.model.bank;

import com.example.springsocial.model.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

// 가계부 내역 관리

@Getter
@NoArgsConstructor
@Entity
public class BankPosts {

    @Id // pk 필드
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bank_id; // 가계부 등록번호

    @Column(nullable = false)
    private Long user_id; // 등록한 유저번호

    @Column(nullable = false)
    private Long category_id; // 카테고리 구분번호

    @Column(nullable = false)
    private String bank_name; // 가계부 항목내역

    @Column(nullable = false)
    private Long bank_num; // 금액

    private boolean bank_flag; // 지출, 수익 타입

    private LocalDate bank_date; // 등록 날짜


    @Builder
    public BankPosts(Long bank_id, Long user_id, Long category_id, String bank_name, Long bank_num, boolean bank_flag, LocalDate bank_date
    ){
        this.bank_id = bank_id;
        this.user_id = user_id;
        this.category_id = category_id;
        this.bank_name = bank_name;
        this.bank_num = bank_num;
        this.bank_flag = bank_flag;
        this.bank_date = bank_date;
    }

}


