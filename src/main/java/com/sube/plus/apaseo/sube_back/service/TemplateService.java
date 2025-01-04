package com.sube.plus.apaseo.sube_back.service;

import com.sube.plus.apaseo.sube_back.model.response.TemplateResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface TemplateService {
    List<TemplateResponse> getAllTemplates();

    TemplateResponse getTemplateById(String id);

    TemplateResponse createTemplate(String name, MultipartFile file) throws IOException;

    TemplateResponse deleteTemplate(String id);

    byte[] downloadFile(String id);
}
