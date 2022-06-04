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

    @Transactional
    public Long save(BankSaveRequestDto requestDto)
    {
        return bankRepository.save(requestDto.toEntity()).getBank_id();
    }

    @Transactional(readOnly = true)
    public BankResponseDto findById(Long id){
        BankPosts entity = bankRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("해당 가계부 내역이 없습니다. id= " + id));

        return new BankResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<BankListResponseDto> findAllDesc(){

        return bankRepository.findAllDesc().stream()
                .map(BankListResponseDto::new)
                .collect(Collectors.toList());

        // posts의 stream을 map을 이용해서 PostsListResponseDto로 변환 -> List 타입으로 반환
    }

    @Transactional
    public void delete (Long id){
        BankPosts bankPostsposts = bankRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 가계부 내역이 없습니다. id = " + id));

        bankRepository.delete(bankPostsposts);
    }
}
