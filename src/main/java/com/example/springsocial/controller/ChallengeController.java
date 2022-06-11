package com.example.springsocial.controller;

import com.example.springsocial.model.challenge.Challenge;
import com.example.springsocial.model.user.User;
import com.example.springsocial.payload.ChlResponseDto;
import com.example.springsocial.payload.ChlSaveRequestDto;
import com.example.springsocial.security.CurrentUser;
import com.example.springsocial.security.UserPrincipal;
import com.example.springsocial.service.challenge.ChallengeService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

@RequiredArgsConstructor
@RestController
public class ChallengeController {

    private final ChallengeService challengeService;

    @PostMapping("/chl/write") // 챌린지 등록
    public Long save(@RequestBody ChlSaveRequestDto requestDto){

        return challengeService.save(requestDto);
    }

    @GetMapping("/chl/list") // 참여중인 챌린지 목록
    public String challenges(Model model, @CurrentUser UserPrincipal userPrincipal){
        model.addAttribute("challenges", challengeService.findAllbyID(userPrincipal.getId()));
        return "challenges";
    }

    @GetMapping("/chl/list/{id}") // 챌린지 세부 내역 조회
    public LinkedHashMap findByID(@PathVariable Long id){
        Challenge chl = challengeService.findChl(id);
        return challengeService.calResult(chl);
    }
}
