package com.example.springsocial.payload;
import com.example.springsocial.model.bank.BankPosts;
import com.example.springsocial.security.CurrentUser;
import com.example.springsocial.security.UserPrincipal;
import lombok.Builder;

import java.time.LocalDate;


public class BankSaveRequestDto {
    private Long user_id;
    private String bank_name;
    private Long bank_num;
    private boolean bank_flag;
    private LocalDate bank_date;
    private Long bank_cat;

    @Builder
    public BankSaveRequestDto(String name, Long num, boolean flag, LocalDate date, Long cat,
                              @CurrentUser UserPrincipal userPrincipal){
        this.user_id = userPrincipal.getId();
        this.bank_name = name;
        this.bank_num = num;
        this.bank_flag = flag;
        this.bank_date = date;
        this.bank_cat = cat;
    }

    public BankPosts toEntity(){
        return BankPosts.builder()
                .user_id(user_id)
                .bank_name(bank_name)
                .bank_num(bank_num)
                .bank_flag(bank_flag)
                .bank_date(bank_date)
                .category_id(bank_cat)
                .build();
    }
}
