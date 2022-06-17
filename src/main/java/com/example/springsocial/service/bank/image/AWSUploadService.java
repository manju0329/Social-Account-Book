package com.example.springsocial.service.bank.image;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.springsocial.model.bank.AWSComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@RequiredArgsConstructor
@Component
public class AWSUploadService implements UploadService {

    private final AmazonS3 amazonS3;
    private final AWSComponent awsComponent;

    // amazonS3을 이용한 이미지 업로드
    @Override
    public void uploadFile(InputStream inputStream, ObjectMetadata objectMetadata, String fileName) {
        amazonS3.putObject(new PutObjectRequest(awsComponent.getBucket(), fileName, inputStream, objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead));
    }

    // 업로드한 이미지의 url 얻기
    @Override
    public String getFileUrl(String fileName) {
        return amazonS3.getUrl(awsComponent.getBucket(), fileName).toString();
    }

}