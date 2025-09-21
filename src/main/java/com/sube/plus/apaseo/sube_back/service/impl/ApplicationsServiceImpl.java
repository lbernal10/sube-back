package com.sube.plus.apaseo.sube_back.service.impl;

import com.sube.plus.apaseo.sube_back.converter.ApplicationsMapper;
import com.sube.plus.apaseo.sube_back.model.Applications;
import com.sube.plus.apaseo.sube_back.model.DocumentApplications;
import com.sube.plus.apaseo.sube_back.model.enums.ApplicationStatus;
import com.sube.plus.apaseo.sube_back.model.enums.DocumentApplicationStatus;
import com.sube.plus.apaseo.sube_back.model.request.ApplicationsRequest;
import com.sube.plus.apaseo.sube_back.model.request.RejectionRequest;
import com.sube.plus.apaseo.sube_back.model.response.ApplicationsResponse;
import com.sube.plus.apaseo.sube_back.model.response.AzureUploadFileResponse;
import com.sube.plus.apaseo.sube_back.model.response.PersonResponse;
import com.sube.plus.apaseo.sube_back.model.response.UserResponse;
import com.sube.plus.apaseo.sube_back.repository.ApplicationsRepository;
import com.sube.plus.apaseo.sube_back.service.ApplicationsService;
import com.sube.plus.apaseo.sube_back.service.AzureBlobStorageService;
import com.sube.plus.apaseo.sube_back.service.PersonService;
import com.sube.plus.apaseo.sube_back.service.UserService;
import com.sube.plus.apaseo.sube_back.util.SendEmail;
import com.sube.plus.apaseo.sube_back.util.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ApplicationsServiceImpl implements ApplicationsService {

    private final ApplicationsRepository applicationsRepository;
    private final ApplicationsMapper applicationsMapper;
    private final AzureBlobStorageService azureBlobStorageService;

    private final SendEmail sendEmail;

    private final UserService userService;

    private final PersonService personService;

    @Override
    public List<ApplicationsResponse> getAllApplications() {
        return applicationsRepository.findAll()
                .stream()
                .map(applicationsMapper::toApplicationsResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ApplicationsResponse getApplicationById(String id) {
        Applications applications = applicationsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Application not found with id: " + id));
        return applicationsMapper.toApplicationsResponse(applications);
    }

    @Override
    public ApplicationsResponse createApplication(ApplicationsRequest request, List<MultipartFile> files) {
        // Mapear documentos con archivos por posición
        List<DocumentApplications> processedDocuments = new ArrayList<>();

        UserResponse userResponse = userService.getUserById(request.getUserId());

        PersonResponse personResponse = personService.getPersonById(userResponse.getPersonId());


        for (int i = 0; i < request.getDocument().size(); i++) {
            DocumentApplications doc = request.getDocument().get(i);

            // Tomar el archivo en la misma posición (si existe)
            MultipartFile matchedFile = (i < files.size()) ? files.get(i) : null;

            if (matchedFile != null && !matchedFile.isEmpty()) {
                try {
                    AzureUploadFileResponse azureUploadFileResponse = azureBlobStorageService.uploadFile(matchedFile);

                    // Guardamos metadata del archivo subido
                    doc.setFileName(azureUploadFileResponse.getName());
                    doc.setUrl(azureUploadFileResponse.getUrl());
                    doc.setTemplateId(doc.getTemplateId());
                    doc.setStatus(DocumentApplicationStatus.PENDING_REVIEW);
                    doc.setRejectionReason(null);
                    doc.setDescription(doc.getDescription());

                } catch (IOException e) {
                    throw new RuntimeException("Error subiendo archivo " + matchedFile.getOriginalFilename(), e);
                }
            }

            processedDocuments.add(doc);
        }

        Applications application = Applications.builder()
                .idAnnouncement(request.getIdAnnouncement())
                .socioEconomic(applicationsMapper.toSocioEconomic(request.getSocioEconomic()))
                .tutor(applicationsMapper.toTutor(request.getTutor()))
                .schoolData(applicationsMapper.toSchoolData(request.getSchoolData()))
                .userId(request.getUserId())
                .status(ApplicationStatus.UNDER_REVIEW)
                .activeSupport(request.isActiveSupport())
                .juveCardDelivered(request.isJuveCardDelivered())
                .document(processedDocuments)
                .createdAt(ZonedDateTime.now())
                .updatedAt(null)
                .folio(generateFolio())
                .build();

        Applications saved = applicationsRepository.save(application);


        // Send email folio
        sendEmail.sendFolioSolicitudByEmail(userResponse.getEmail(), saved.getFolio(), personResponse.getFullName());

        return applicationsMapper.toApplicationsResponse(saved);
    }

    @Override
    public ApplicationsResponse updateDocumentById(String applicationId, String documentId, MultipartFile file) {
        Applications application = applicationsRepository.findById(applicationId)
                .orElseThrow(() -> new NotFoundException("Application not found with id: " + applicationId));

        List<DocumentApplications> documents = application.getDocument();

        // Buscar documento por id
        DocumentApplications doc = documents.stream()
                .filter(d -> d.getId().equals(documentId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Document not found with id: " + documentId));

        try {
            AzureUploadFileResponse azureUploadFileResponse = azureBlobStorageService.uploadFile(file);

            // Actualizar metadata del documento
            doc.setFileName(azureUploadFileResponse.getName());
            doc.setUrl(azureUploadFileResponse.getUrl());
            doc.setStatus(DocumentApplicationStatus.PENDING_REVIEW);
            doc.setRejectionReason(null);

        } catch (IOException e) {
            throw new RuntimeException("Error uploading file " + file.getOriginalFilename(), e);
        }

        application.setUpdatedAt(ZonedDateTime.now());
        Applications updated = applicationsRepository.save(application);

        return applicationsMapper.toApplicationsResponse(updated);
    }

    @Override
    public ApplicationsResponse rejectDocument(String applicationId, String documentId, RejectionRequest rejectionRequest) {
        if (rejectionRequest.getReason() == null || rejectionRequest.getReason().isEmpty()) {
            throw new IllegalArgumentException("Rejection reason is required");
        }

        Applications application = applicationsRepository.findById(applicationId)
                .orElseThrow(() -> new NotFoundException("Application not found with id: " + applicationId));

        List<DocumentApplications> documents = application.getDocument();

        DocumentApplications doc = documents.stream()
                .filter(d -> d.getId().equals(documentId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Document not found with id: " + documentId));

        doc.setStatus(DocumentApplicationStatus.REJECTED);
        doc.setRejectionReason(rejectionRequest.getReason());
        application.setUpdatedAt(ZonedDateTime.now());

        Applications updated = applicationsRepository.save(application);

        return applicationsMapper.toApplicationsResponse(updated);
    }

    @Override
    public ApplicationsResponse approveDocument(String applicationId, String documentId) {
        Applications application = applicationsRepository.findById(applicationId)
                .orElseThrow(() -> new NotFoundException("Application not found with id: " + applicationId));

        List<DocumentApplications> documents = application.getDocument();

        DocumentApplications doc = documents.stream()
                .filter(d -> d.getId().equals(documentId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Document not found with id: " + documentId));

        doc.setStatus(DocumentApplicationStatus.APPROVED);
        doc.setRejectionReason(null); // Limpiamos cualquier razón de rechazo
        application.setUpdatedAt(ZonedDateTime.now());

        Applications updated = applicationsRepository.save(application);

        return applicationsMapper.toApplicationsResponse(updated);
    }

    @Override
    public List<ApplicationsResponse> getApplicationsByUserId(String userId) {
        return applicationsRepository.findByUserId(userId)
                .stream()
                .map(applicationsMapper::toApplicationsResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ApplicationsResponse> getApplicationsByStatus(ApplicationStatus status) {
        return applicationsRepository.findByStatus(status)
                .stream()
                .map(applicationsMapper::toApplicationsResponse)
                .collect(Collectors.toList());
    }

    private String generateFolio() {
        LocalDateTime now = LocalDateTime.now();
        return "SUBE-" + now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
    }

}
