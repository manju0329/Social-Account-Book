package com.example.springsocial.payload;


import com.example.springsocial.model.challenge.ChallengeMember;
import lombok.Getter;

@Getter
public class ChlListResponseDto {

    private Long chl_id;
    private Long user_id;

    public ChlListResponseDto(ChallengeMember entity){
        this.chl_id = entity.getChl_id();
        this.user_id = entity.getUser_id();
    }


}
