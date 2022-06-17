package com.example.springsocial.service.bank.image;

import com.amazonaws.services.s3.model.ObjectMetadata;

import java.io.InputStream;

// 업로드 관련 인터페이스 분리
public interface UploadService {

    void uploadFile(InputStream inputStream, ObjectMetadata objectMetadata, String fileName);

    String getFileUrl(String fileName);

}