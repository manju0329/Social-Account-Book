package com.example.springsocial.payload;

import com.example.springsocial.model.challenge.ChallengeMember;
import com.example.springsocial.security.CurrentUser;
import com.example.springsocial.security.UserPrincipal;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class ChlMemSaveRequestDto {

    private Long chl_id;
    private Long user_id;

    @Builder
    public ChlMemSaveRequestDto(Long chl_id, @CurrentUser UserPrincipal userPrincipal){
        this.chl_id = chl_id;
        this.user_id = userPrincipal.getId();
    }

    public ChallengeMember toEntity(){
        return ChallengeMember.builder()
                .chl_id(chl_id)
                .user_id(user_id)
                .build();
    }

}
