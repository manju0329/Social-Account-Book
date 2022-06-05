package com.example.springsocial.model.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// 친구 관계 관리
// 유저 A -> 유저 B를 친구추가하면 user_from = A , user_to = B / user_from = B , user_to = A 로 쌍방향 저장

@Entity
@Getter
@Setter
@Table(name = "friends")
public class Friend {

    @Id
    private Long seq;

    @Column(nullable = false)
    private Long user_from;

    @Column(nullable = false)
    private Long user_to;


    @Builder
    public Friend(Long seq, Long user_from, Long user_to){
        this.seq = seq;
        this.user_from = user_from;
        this.user_to = user_to;
    }
}
