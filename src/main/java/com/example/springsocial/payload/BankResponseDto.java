package com.example.springsocial.payload;


import com.example.springsocial.model.bank.BankPosts;
import com.example.springsocial.model.posts.Posts;
import lombok.Getter;

import javax.persistence.Column;
import java.time.LocalDate;

@Getter
public class BankResponseDto {

    private Long bank_id; // 가계부 등록번호
    private Long user_id; // 등록한 유저번호
    private Long category_id; // 카테고리 구분번호
    private String bank_name; // 가계부 항목내역
    private Long bank_num; // 금액
    private boolean bank_flag; // 지출, 수익 타입
    private LocalDate bank_date; // 등록 날짜

    public BankResponseDto(BankPosts entity){
        this.bank_id = entity.getBank_id();
        this.user_id = entity.getUser_id();
        this.category_id = entity.getCategory_id();
        this.bank_name = entity.getBank_name();
        this.bank_num = entity.getBank_num();
        this.bank_flag = entity.isBank_flag();
        this.bank_date = entity.getBank_date();
    }
}
