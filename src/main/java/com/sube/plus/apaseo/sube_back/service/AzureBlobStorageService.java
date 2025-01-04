package com.sube.plus.apaseo.sube_back.service;

import com.sube.plus.apaseo.sube_back.model.response.AzureUploadFileResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AzureBlobStorageService {
    AzureUploadFileResponse uploadFile(MultipartFile file) throws IOException;

    byte[] downloadFile(String fileName);

    AzureUploadFileResponse updateFile(String fileName, MultipartFile file) throws IOException;

    void deleteFile(String fileName);
}
