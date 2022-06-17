package com.example.springsocial.payload;

import com.example.springsocial.model.user.Friend;
import lombok.Builder;

public class    FriendSaveRequestDto {

    private Long seq;

    private Long user_from;

    private Long user_to;

    @Builder
    public FriendSaveRequestDto(Long seq, Long user_from, Long user_to){
        this.seq = seq;
        this.user_from = user_from;
        this.user_to = user_to;
    }

    public Friend toEntity(){

        return Friend.builder()
                .seq(seq)
                .user_from(user_from)
                .user_to(user_to)
                .build();
    }
}
