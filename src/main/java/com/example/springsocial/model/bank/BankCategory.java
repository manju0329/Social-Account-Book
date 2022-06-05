package com.example.springsocial.model.bank;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

//가계부 카테고리 관리
// 1. 식비 2. 교통비 3. 문화/여가 4. 주거/통신 5. 기타생활비
@Getter
@NoArgsConstructor
@Entity
public class BankCategory {

    @Id
    private Long category_id;

    private String category_name;

    @Builder
    public BankCategory(Long id, String name){
        this.category_id = id;
        this.category_name = name;
    }
}
