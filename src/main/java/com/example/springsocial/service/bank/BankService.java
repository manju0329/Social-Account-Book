package com.example.springsocial.service.bank;

import com.example.springsocial.model.bank.BankPosts;
import com.example.springsocial.payload.*;
import com.example.springsocial.repository.BankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BankService {

    private final BankRepository bankRepository;

    // 가계부 저장
    @Transactional
    public Long save(BankSaveRequestDto requestDto)
    {
        return bankRepository.save(requestDto.toEntity()).getBankid();
    }

    // 특정 가계부 조회
    @Transactional(readOnly = true)
    public BankResponseDto findOne(Long user_id, Long bank_id){
        BankPosts entity = bankRepository.findByUseridAndBankid(user_id, bank_id).orElseThrow(()->
                new IllegalArgumentException("해당 가계부 내역이 없습니다. bank_id= " + bank_id));

        return new BankResponseDto(entity);
    }

    // 전체 가계부 조회
    @Transactional(readOnly = true)
    public List<BankListResponseDto> findAll(Long user_id){

        return bankRepository.findByUserid(user_id).stream()
                .map(BankListResponseDto::new)
                .collect(Collectors.toList());


    }

    // 특정 가계부 삭제
    @Transactional
    public void delete (Long id){
        BankPosts bankPostsposts = bankRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 가계부 내역이 없습니다. id = " + id));

        bankRepository.delete(bankPostsposts);
    }
}
