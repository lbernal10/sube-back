package com.sube.plus.apaseo.sube_back.service.impl;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.sube.plus.apaseo.sube_back.model.response.AzureUploadFileResponse;
import com.sube.plus.apaseo.sube_back.service.AzureBlobStorageService;
import com.sube.plus.apaseo.sube_back.util.exceptions.BadRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AzureBlobStorageServiceImpl implements AzureBlobStorageService {

    private final BlobServiceClient blobServiceClient;

    @Value("${azure.storage.container-name}")
    private String containerName;

    private BlobContainerClient containerClient;

    @PostConstruct
    private void initializeContainerClient() {
        this.containerClient = blobServiceClient.getBlobContainerClient(containerName);
    }

    @Override
    public AzureUploadFileResponse uploadFile(MultipartFile file) throws IOException {
        // Get the original file name
        String originalFileName = file.getOriginalFilename();

        // Validate that the file name is not null
        if (originalFileName == null) {
            throw new BadRequestException("The file does not have a valid name.");
        }

        // Append the current date and time to the file name
        String currentDateTime = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
        String newFileName = currentDateTime + "_" + originalFileName;

        // Create a BlobClient with the new file name
        BlobClient blobClient = containerClient.getBlobClient(newFileName);

        // Upload the file to Blob Storage
        blobClient.upload(file.getInputStream(), file.getSize(), true);

        log.info("File '{}' uploaded successfully as '{}'.", originalFileName, newFileName);

        // Return the Blob URL and the new file name
        return AzureUploadFileResponse
                .builder()
                    .url(blobClient.getBlobUrl())
                    .name(newFileName)
                .build();
    }

    @Override
    public byte[] downloadFile(String fileName) {
        BlobClient blobClient = containerClient.getBlobClient(fileName);
        if (!blobClient.exists()) {
            throw new IllegalArgumentException("File not found: " + fileName);
        }
        return blobClient.downloadContent().toBytes();
    }

    @Override
    public AzureUploadFileResponse updateFile(String fileName, MultipartFile file) throws IOException {
        deleteFile(fileName);
        return uploadFile(file);
    }

    @Override
    public void deleteFile(String fileName) {
        BlobClient blobClient = containerClient.getBlobClient(fileName);
        if (blobClient.exists()) {
            blobClient.delete();
        } else {
            throw new IllegalArgumentException("File not found: " + fileName);
        }
    }

}