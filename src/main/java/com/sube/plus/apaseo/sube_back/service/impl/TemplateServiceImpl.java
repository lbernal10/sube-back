package com.sube.plus.apaseo.sube_back.service.impl;

import com.sube.plus.apaseo.sube_back.converter.TemplateMapper;
import com.sube.plus.apaseo.sube_back.model.Template;
import com.sube.plus.apaseo.sube_back.model.enums.TemplateStatus;
import com.sube.plus.apaseo.sube_back.model.response.AzureUploadFileResponse;
import com.sube.plus.apaseo.sube_back.model.response.TemplateResponse;
import com.sube.plus.apaseo.sube_back.repository.TemplateRepository;
import com.sube.plus.apaseo.sube_back.service.AzureBlobStorageService;
import com.sube.plus.apaseo.sube_back.service.TemplateService;
import com.sube.plus.apaseo.sube_back.util.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TemplateServiceImpl implements TemplateService {

    private final TemplateRepository templateRepository;

    private final TemplateMapper templateMapper;

    private final AzureBlobStorageService azureBlobStorageService;

    @Override
    public List<TemplateResponse> getAllTemplates() {
        List<Template> templates = templateRepository.findAll();
        return templates.stream()
                .filter(template -> TemplateStatus.ACTIVE.equals(template.getStatus()))  // Filtra los templates con estado ACTIVE
                .map(templateMapper::toTemplateResponse)  // Mapea los templates activos a TemplateResponse
                .collect(Collectors.toList());
    }

    @Override
    public TemplateResponse getTemplateById(String id) {
        Template template = templateRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Template not found with id: " + id));
        return templateMapper.toTemplateResponse(template);
    }

    @Override
    public TemplateResponse createTemplate(String name, MultipartFile file) throws IOException {
        AzureUploadFileResponse azureUploadFileResponse = azureBlobStorageService.uploadFile(file);

        Template template = Template
                .builder()
                    .name(name)
                    .fileName(azureUploadFileResponse.getName())
                    .url(azureUploadFileResponse.getUrl())
                    .status(TemplateStatus.ACTIVE)
                .build();

        Template savedTemplate = templateRepository.save(template);

        return templateMapper.toTemplateResponse(savedTemplate);
    }

    @Override
    public TemplateResponse updateTemplate(String id, String name, MultipartFile file) throws IOException {
        log.info("Fetching template with id: {}", id);

        Template existingTemplate = templateRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Template not found with id: " + id));
        log.debug("Updating Template with id: {}", existingTemplate.getId());

        log.debug("Template: {}", existingTemplate);

        // Elimina del storage el antiguo
        azureBlobStorageService.deleteFile(existingTemplate.getFileName());

        // Sube el nuevo archivo
        AzureUploadFileResponse azureUploadFileResponse = azureBlobStorageService.uploadFile(file);

        // Actualiza los datos
        existingTemplate.setName(name);
        existingTemplate.setFileName(azureUploadFileResponse.getName());
        existingTemplate.setUrl(azureUploadFileResponse.getUrl());

        // Guarda los datos
        Template updatedTemplate = templateRepository.save(existingTemplate);

        return templateMapper.toTemplateResponse(updatedTemplate);
    }

    @Override
    public TemplateResponse deleteTemplate(String id) {
        Template existingTemplate = templateRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Template not found with id: " + id));

        // Elimina del storage el antiguo
        azureBlobStorageService.deleteFile(existingTemplate.getFileName());

        existingTemplate.setStatus(TemplateStatus.INACTIVE);

        // Guarda los datos
        Template updatedTemplate = templateRepository.save(existingTemplate);

        return templateMapper.toTemplateResponse(updatedTemplate);
    }

    @Override
    public byte[] downloadFile(String fileName) {
        return azureBlobStorageService.downloadFile(fileName);
    }
}