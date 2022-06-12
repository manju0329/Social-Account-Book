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

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Challenge.class) // 양방향 연결
    @JoinColumn(name="chlid")
    private Long chlid;

    @Column(name = "user_id")
    private Long userid;

    @Builder
    public ChallengeMember(Long chl_seq, Long chl_id, Long user_id){
        this.chl_seq = chl_seq;
        this.chlid = chl_id;
        this.userid = user_id;
    }
}
