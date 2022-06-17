package com.example.springsocial.payload;

import com.example.springsocial.model.bank.BankPosts;
import lombok.Getter;

import java.time.LocalDate;

// 가계부 내역 리스트 DTO

@Getter
public class BankListResponseDto {


    private Long bank_id; // 가계부 등록번호
    private Long user_id; // 등록한 유저번호
    private Long category_id; // 카테고리 구분번호
    private String bank_name; // 가계부 항목내역
    private Long bank_num; // 금액
    private boolean bank_flag; // 지출, 수익 타입
    private LocalDate bank_date; // 등록 날짜

    public BankListResponseDto(BankPosts entity){
        this.bank_id = entity.getBankid();
        this.user_id = entity.getUserid();
        this.category_id = entity.getCategoryid();
        this.bank_name = entity.getBank_name();
        this.bank_num = entity.getBank_num();
        this.bank_flag = entity.isBank_flag();
        this.bank_date = entity.getBank_date();
    }
}
