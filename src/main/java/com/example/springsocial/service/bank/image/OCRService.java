package com.example.springsocial.service.bank.image;


import com.example.springsocial.payload.BankResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OCRService {

    // 캡쳐 이미지 은행 별로 구분해서 변환 후 BankPosts가 저장된 list 반환
    
    private final KBService kbService;
    private final NHService nhService;
    private final TOService toService;
    private final SHService shService;
    private final WRService wrService;

    public List list;

    public List<BankResponseDto> convert(String imgUrl, int tag){
        switch (tag){
            case 1:
                list = kbService.convert(imgUrl);
                break;
            case 2:
                list = nhService.convert(imgUrl);
                break;
            case 3:
                list = toService.convert(imgUrl);
                break;
            case 4:
                list = shService.convert(imgUrl);
                break;
            case 5:
                list = wrService.convert(imgUrl);
                break;
        }
        return list;
    }
}
