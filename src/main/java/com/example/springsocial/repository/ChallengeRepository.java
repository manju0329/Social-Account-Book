package com.example.springsocial.repository;

import com.example.springsocial.model.challenge.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {

    @Query("SELECT c.chl_id, u.id FROM user u INNER JOIN challengemember cm ON cm.user_id = u.id" +
            "INNER JOIN challenge c ON c.chl_id = cm.chl_id WHERE name = user_name")
    List<Challenge> findAllDesc();
}
