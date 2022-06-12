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

    @Transactional
    public Long invite(Long id, FriendSaveRequestDto requestDto){

        requestDto.toEntity().setUserfrom(id);
        return friendRepository.save(requestDto.toEntity()).getSeq();
    }

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
