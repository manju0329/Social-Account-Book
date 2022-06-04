package com.example.springsocial.controller;

import com.example.springsocial.payload.BankListResponseDto;
import com.example.springsocial.payload.BankResponseDto;
import com.example.springsocial.payload.BankSaveRequestDto;
import com.example.springsocial.security.CurrentUser;
import com.example.springsocial.security.UserPrincipal;
import com.example.springsocial.service.bank.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BankController {

    private final BankService bankService;

    @PostMapping("/bank/write") //가계부 등록
    public Long save(@RequestBody BankSaveRequestDto requestDto){
        return bankService.save(requestDto);
    }

    @GetMapping("/bank") //유저의 전체 가계부 조회
    public List<BankListResponseDto> findAllDesc(@CurrentUser UserPrincipal userPrincipal){
        return bankService.findAllDesc();
    }

    @GetMapping("/bank/{id}") //유저의 특정 가계부 조회
    public BankResponseDto findByID(@PathVariable Long id)
    {
        return bankService.findById(id);
    }


}
