package com.example.springsocial.controller;

import com.example.springsocial.payload.BankResponseDto;
import com.example.springsocial.payload.BankSaveRequestDto;
import com.example.springsocial.security.CurrentUser;
import com.example.springsocial.security.UserPrincipal;
import com.example.springsocial.service.bank.BankService;
import com.example.springsocial.service.bank.image.FileUploadService;
import com.example.springsocial.service.bank.image.OCRService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class BankController {

    private final BankService bankService;
    private final FileUploadService fileUploadService;
    private final OCRService ocrService;

    @PostMapping("/bank/write") //가계부 등록
    public Long save(@RequestBody BankSaveRequestDto requestDto){
        return bankService.save(requestDto);
    }

    @PostMapping("/bank/write/img") // 이미지 방식 등록
    public String upload(Model model,@RequestPart MultipartFile multipartFile, @RequestParam int tag) throws IOException {
        String imgUrl = fileUploadService.uploadImage(multipartFile);
        model.addAttribute("img", ocrService.convert(imgUrl, tag));
        return "img";
    }

    @GetMapping("/bank") //유저의 전체 가계부 조회
    public String BankPosts(Model model, @CurrentUser UserPrincipal userPrincipal){
        model.addAttribute("bank", bankService.findAll(userPrincipal.getId()));
        return "bank";
    }

    @GetMapping("/bank/{id}") //유저의 특정 가계부 조회(id=글번호)
    public BankResponseDto findByUser_idAndBank_id
            (@PathVariable Long id, @CurrentUser UserPrincipal userPrincipal)
    {
        return bankService.findOne(userPrincipal.getId(), id);
    }

    @DeleteMapping("/bank/{id}") // 가계부 삭제
    public Long delete(@PathVariable Long id){

        bankService.delete(id);
        return id;
    }


}
