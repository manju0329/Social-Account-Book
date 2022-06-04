package com.example.springsocial.payload;

import com.example.springsocial.model.challenge.Challenge;
import lombok.Getter;

@Getter
public class ChlResponseDto {

    private Long chl_id; // 챌린지 번호
    private boolean chl_result; // 챌린지 진행상황(진행중 / 성공 / 실패)
    private Long chl_cat; // 챌린지를 진행할 카테고리
    private Long chl_fre; // 챌린지를 진행할 카테고리의 사용 횟수

    public ChlResponseDto(Challenge entity){
       this.chl_id = entity.getChl_id();
       this.chl_result = entity.isChl_result();
       this.chl_cat = entity.getChl_cat();
       this.chl_fre = entity.getChl_fre();
    }

}
