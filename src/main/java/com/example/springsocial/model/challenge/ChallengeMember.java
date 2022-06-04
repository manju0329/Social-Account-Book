package com.example.springsocial.model.challenge;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// user 와 challenge join을 위한 entity
@Getter
@NoArgsConstructor
@Entity
@Table(name = "challengemember")
public class ChallengeMember {

    @Id
    private Long chl_id;

    @Id
    private Long user_id;

    @Builder
    public ChallengeMember(Long chl_id, Long user_id){
        this.chl_id = chl_id;
        this.user_id = user_id;
    }
}
