package com.example.springsocial.model.challenge;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

// 챌린지 관리

@Getter
@NoArgsConstructor
@Entity
@Table(name = "challenge")
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "chl_id")
    private Long chlid; // 챌린지 번호

    @OneToMany(mappedBy = "chlid", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<ChallengeMember> challengeMember; // 1:many 관계의 챌린지-챌린지멤버

    private boolean chl_result; // 챌린지 진행상황(진행중 / 성공 / 실패)

    private Long chl_cat; // 챌린지를 진행할 카테고리

    private Long chl_fre; // 챌린지를 진행할 카테고리의 사용 횟수

    @Builder
    public Challenge(Long chl_id, boolean chl_result, Long chl_cat, Long chl_fre, Collection<ChallengeMember> challengeMember){
        this.chlid = chl_id;
        this.chl_result = chl_result;
        this.chl_cat = chl_cat;
        this.chl_fre = chl_fre;
        this.challengeMember = challengeMember;
    }
}
