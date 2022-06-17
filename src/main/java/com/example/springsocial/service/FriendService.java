package com.example.springsocial.service;

import com.example.springsocial.model.user.Friend;
import com.example.springsocial.model.user.User;
import com.example.springsocial.payload.FriendSaveRequestDto;
import com.example.springsocial.repository.FriendRepository;
import com.example.springsocial.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final UserRepository userRepository;
    private final FriendRepository friendRepository;

    // 친구 추가 -> 친구 관리 Entity에 저장
    @Transactional
    public Long invite(Long id, FriendSaveRequestDto requestDto){

        requestDto.toEntity().setUserfrom(id);
        return friendRepository.save(requestDto.toEntity()).getSeq();
    }

    // 친구 삭제 -> 양 쪽 모두에게 삭제
    @Transactional
    public void delete(Long user_from, Long user_to){
        Friend friend = friendRepository.findByUsertoAndUserfrom(user_from, user_to)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 친구입니다 id = " + user_to));
        Friend friend_r = friendRepository.findByUsertoAndUserfrom(user_to, user_from)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 친구입니다 id = " + user_to));

        friendRepository.delete(friend);
        friendRepository.delete(friend_r);
    }


}
