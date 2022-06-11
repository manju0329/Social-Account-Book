package com.example.springsocial.model.challenge;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// user 와 challenge join을 위한 entity
@Getter
@NoArgsConstructor
@Entity
@Table(name = "challengemember")
public class ChallengeMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chl_seq;

    @ManyToOne(fetch = FetchType.LAZY) // 양방향 연결
    @Column(name="chl_id")
    private Long chl_id;

    private Long user_id;

    @Builder
    public ChallengeMember(Long chl_seq, Long chl_id, Long user_id){
        this.chl_seq = chl_seq;
        this.chl_id = chl_id;
        this.user_id = user_id;
    }
}
