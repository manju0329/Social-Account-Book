package com.example.springsocial.service.bank.image;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import com.example.springsocial.payload.BankResponseDto;
import com.example.springsocial.repository.BankRepository;
import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.Block;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Feature.Type;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.cloud.vision.v1.Page;
import com.google.cloud.vision.v1.Paragraph;
import com.google.cloud.vision.v1.Symbol;
import com.google.cloud.vision.v1.TextAnnotation;
import com.google.cloud.vision.v1.Word;
import com.google.protobuf.ByteString;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WRService {

    public final BankRepository bankRepository;
    public BankResponseDto bankResponseDto;
    public List<BankResponseDto> list;

    public List<BankResponseDto> convert(String imgUrl){
        try {
            List<AnnotateImageRequest> requests = new ArrayList<>();
            ByteString imgBytes = ByteString.readFrom(new FileInputStream(imgUrl));
            Image img = Image.newBuilder().setContent(imgBytes).build();

            Feature feat = Feature.newBuilder().setType(Type.TEXT_DETECTION).build();

            AnnotateImageRequest request = AnnotateImageRequest.newBuilder()
                    .addFeatures(feat).setImage(img).build();
            requests.add(request);

            try (ImageAnnotatorClient client = ImageAnnotatorClient.create())
            {
                BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
                List<AnnotateImageResponse> responses = response.getResponsesList();
                for (AnnotateImageResponse res : responses)
                {
                    if (res.hasError()) {
                        System.out.printf("Error: %s\n", res.getError().getMessage());
                        return null;
                    }
                    TextAnnotation annotation = res.getFullTextAnnotation();

                    for (Page page: annotation.getPagesList()) {
                        String pageText = "";
                        int b_idx = 1;
                        int idx_ = 1;
                        for (Block block : page.getBlocksList()) {
                            String blockText = "";
                            int idx = 0;
                            int in_idx = 1;
                            for (Paragraph para : block.getParagraphsList()) {
                                String paraText = "";
                                for (Word word: para.getWordsList()) {
                                    String wordText = "";
                                    for (Symbol symbol: word.getSymbolsList()) {
                                        wordText = wordText + symbol.getText();
                                    }
                                    paraText = String.format("%s %s", paraText, wordText);
                                }

                                if(b_idx == 7 && idx >= 1 && (paraText.contains("출금") || paraText.contains("입금"))){
                                    if(paraText.contains("잔액")){
                                        bankResponseDto.setBank_num(Long.parseLong(paraText.substring(0,12)));
                                    }else
                                        bankResponseDto.setBank_num(Long.parseLong(paraText.substring(0, 9)));

                                    idx_++;

                                }else if (b_idx == 7 && idx >=1 && !(paraText.startsWith(" 잔액"))){
                                    bankResponseDto.setBank_name(paraText.trim());
                                    in_idx++;
                                }

                                idx++;
                                blockText = blockText + paraText;
                                list.add(bankResponseDto);
                            }
                            pageText = pageText + blockText;
                            b_idx++;
                        }
                    }
                }
            }
        } catch(Exception e)
        {
            e.printStackTrace();
        }

        return list;
    }

}
