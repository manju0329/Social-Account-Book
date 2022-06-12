package com.example.springsocial.controller;

import com.example.springsocial.exception.ResourceNotFoundException;
import com.example.springsocial.model.user.User;
import com.example.springsocial.payload.FriendSaveRequestDto;
import com.example.springsocial.repository.UserRepository;
import com.example.springsocial.security.CurrentUser;
import com.example.springsocial.security.UserPrincipal;
import com.example.springsocial.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    private FriendService friendService;

    @GetMapping("/user/me") //로그인한 유저정보 확인
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }

    @GetMapping("/friend/{id}") //유저 검색
    public User getFindUser(@PathVariable String email){
        Optional<User> user = userRepository.findByEmail(email);

        return user.get();
    }

    @DeleteMapping("/friend/{id}") // 친구 삭제
    public Long deleteFriend(@RequestBody FriendSaveRequestDto requestDto,
                               @CurrentUser UserPrincipal userPrincipal){
        friendService.delete(userPrincipal.getId(), requestDto.toEntity().getUserto());

        return requestDto.toEntity().getUserto();
    }

    @PutMapping("/friend/{id}") // 친구 추가
    public Long inviteFriend(@RequestBody FriendSaveRequestDto requestDto,
                             @CurrentUser UserPrincipal userPrincipal){
        return friendService.invite(userPrincipal.getId(), requestDto);
    }
}
