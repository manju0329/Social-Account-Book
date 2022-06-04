package com.example.springsocial.model.challenge;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

// 챌린지 관리

@Getter
@NoArgsConstructor
@Entity
@Table(name = "challenge")
public class Challenge {

    @Id
    private Long chl_id; // 챌린지 번호

    private boolean chl_result; // 챌린지 진행상황(진행중 / 성공 / 실패)

    private Long chl_cat; // 챌린지를 진행할 카테고리

    private Long chl_fre; // 챌린지를 진행할 카테고리의 사용 횟수

    @Builder
    public Challenge(Long chl_id, boolean chl_result, Long chl_cat, Long chl_fre){
        this.chl_id = chl_id;
        this.chl_result = chl_result;
        this.chl_cat = chl_cat;
        this.chl_fre = chl_fre;
    }
}
