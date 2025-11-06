package com.sube.plus.apaseo.sube_back.service.impl;

import com.sube.plus.apaseo.sube_back.converter.ApplicationsMapper;
import com.sube.plus.apaseo.sube_back.model.Applications;
import com.sube.plus.apaseo.sube_back.model.DocumentApplications;
import com.sube.plus.apaseo.sube_back.model.enums.ApplicationStatus;
import com.sube.plus.apaseo.sube_back.model.enums.DocumentApplicationStatus;
import com.sube.plus.apaseo.sube_back.model.enums.DocumentApplicationType;
import com.sube.plus.apaseo.sube_back.model.enums.DocumentType;
import com.sube.plus.apaseo.sube_back.model.request.*;
import com.sube.plus.apaseo.sube_back.model.response.*;
import com.sube.plus.apaseo.sube_back.repository.ApplicationsRepository;
import com.sube.plus.apaseo.sube_back.service.*;
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

    private final AnnouncementService announcementService;
    @Override
    public List<ApplicationsResponse> getAllApplications() {
        return applicationsRepository.findAll()
                .stream()
                .map(application -> {
                    ApplicationsResponse applicationsResponse = applicationsMapper.toApplicationsResponse(application);

                    AnnouncementResponse programResponse = announcementService.getAnnouncementById(application.getIdAnnouncement());

                    applicationsResponse.setAnnouncement(programResponse);

                    return applicationsResponse;
                })
                .collect(Collectors.toList());
    }

    @Override
    public ApplicationsResponse getApplicationById(String id) {
        Applications applications = applicationsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Application not found with id: " + id));

        ApplicationsResponse applicationsResponse = applicationsMapper.toApplicationsResponse(applications);

        AnnouncementResponse announcementResponse = announcementService.getAnnouncementById(applications.getIdAnnouncement());
        applicationsResponse.setAnnouncement(announcementResponse);

        return applicationsResponse;
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
    public ApplicationsResponse updateApplicationInfo(String applicationId, ApplicationsUpdateRequest request) {
        Applications existing = applicationsRepository.findById(applicationId)
                .orElseThrow(() -> new NotFoundException("Application not found with id: " + applicationId));

        if (request.getSocioEconomic() != null) {
            if (existing.getSocioEconomic() == null) {
                existing.setSocioEconomic(applicationsMapper.toSocioEconomic(request.getSocioEconomic()));
            } else {
                applicationsMapper.updateSocioEconomicFromRequest(request.getSocioEconomic(), existing.getSocioEconomic());
            }
        }


        if (request.getTutor() != null) {
            if (existing.getTutor() == null) {
                existing.setTutor(applicationsMapper.toTutor(request.getTutor()));
            } else {
                applicationsMapper.updateTutorFromRequest(request.getTutor(), existing.getTutor());
            }
        }

        if (request.getSchoolData() != null) {
            if (existing.getSchoolData() == null) {
                existing.setSchoolData(applicationsMapper.toSchoolData(request.getSchoolData()));
            } else {
                applicationsMapper.updateSchoolDataFromRequest(request.getSchoolData(), existing.getSchoolData());
            }
        }

        // Cambiar el estado a "EN REVISIÓN"
        existing.setStatus(ApplicationStatus.UNDER_REVIEW);
        existing.setStatusReason("");
        existing.setUpdatedAt(ZonedDateTime.now());

        Applications updated = applicationsRepository.save(existing);

        return applicationsMapper.toApplicationsResponse(updated);
    }

    @Override
    public ApplicationsResponse updateApplicationStatus(String applicationId, ApplicationStatusUpdateRequest request) {
        Applications existing = applicationsRepository.findById(applicationId)
                .orElseThrow(() -> new NotFoundException("Application not found with id: " + applicationId));


        if (request.getStatus() != null) {
            switch (request.getStatus()) {
                case REJECTED:
                case RETURNED:
                    // REJECTED y RETURNED requieren statusReason
                    if (request.getStatusReason() == null || request.getStatusReason().isEmpty()) {
                        throw new IllegalArgumentException("StatusReason is required when rejecting an application.");
                    }
                    existing.setStatus(ApplicationStatus.REJECTED);
                    existing.setStatusReason(request.getStatusReason());
                    break;

                case CANCELED:
                    // Si es CANCELED, se asigna automáticamente
                    existing.setStatus(ApplicationStatus.CANCELED);
                    existing.setStatusReason("Application canceled by the reviewer.");
                    break;

                default:
                    // Otros estatus se actualizan normalmente
                    existing.setStatus(request.getStatus());
                    existing.setStatusReason(request.getStatusReason());
                    break;
            }
        } else {
            // Si no se envía status, no hacemos nada
            existing.setStatusReason(request.getStatusReason());
        }

        existing.setUpdatedAt(ZonedDateTime.now());

        Applications updated = applicationsRepository.save(existing);
        return applicationsMapper.toApplicationsResponse(updated);
    }

    @Override
    public ApplicationsResponse uploadEvidenceDocument(String applicationId, String name, String description, MultipartFile file) {
        Applications existing = applicationsRepository.findById(applicationId)
                .orElseThrow(() -> new NotFoundException("Application not found with id: " + applicationId));

        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File must not be empty.");
        }

        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || !originalFilename.contains(".")) {
            throw new IllegalArgumentException("File must have a valid extension.");
        }

        String extension = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
        DocumentType documentType = null;

        for (DocumentType dt : DocumentType.values()) {
            if (dt.getDescription().equals(extension)) {
                documentType = dt;
                break;
            }
        }

        if (documentType == null) {
            throw new IllegalArgumentException("File type not supported. Allowed types: PDF, WORD, EXCEL.");
        }

        try {
            AzureUploadFileResponse azureUploadFileResponse = azureBlobStorageService.uploadFile(file);

            DocumentApplications document = DocumentApplications.builder()
                    .name(name)
                    .description(description)
                    .fileName(azureUploadFileResponse.getName())
                    .url(azureUploadFileResponse.getUrl())
                    .status(DocumentApplicationStatus.PENDING_REVIEW)
                    .typeDocumentProgram(DocumentApplicationType.EVIDENCE)
                    .documentType(documentType)
                    .requireTemplate(false)
                    .rejectionReason(null)
                    .build();
            existing.getDocument().add(document);
            existing.setUpdatedAt(ZonedDateTime.now());

            Applications saved = applicationsRepository.save(existing);

            return applicationsMapper.toApplicationsResponse(saved);

        } catch (IOException e) {
            throw new RuntimeException("Error subiendo archivo " + file.getOriginalFilename(), e);
        }
    }

    @Override
    public ApplicationsResponse removeDocument(String applicationId, String documentId) {
        Applications existing = applicationsRepository.findById(applicationId)
                .orElseThrow(() -> new NotFoundException("Application not found with id: " + applicationId));

        DocumentApplications document = existing.getDocument().stream()
                .filter(d -> d.getId().equals(documentId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Document not found with id: " + documentId));

        try {
            existing.getDocument().remove(document);
            existing.setStatus(ApplicationStatus.UNDER_REVIEW);
            existing.setUpdatedAt(ZonedDateTime.now());

            Applications saved = applicationsRepository.save(existing);

            azureBlobStorageService.deleteFile(document.getFileName());

            return applicationsMapper.toApplicationsResponse(saved);

        } catch (Exception e) {
            throw new RuntimeException("Error eliminando archivo de Azure: " + document.getFileName(), e);
        }

    }

    @Override
    public List<ApplicationsResponse> getApplicationsByUserId(String userId) {
        return applicationsRepository.findByUserId(userId)
                .stream()
                .map(application -> {
                    ApplicationsResponse applicationsResponse = applicationsMapper.toApplicationsResponse(application);

                    AnnouncementResponse announcementResponse = announcementService.getAnnouncementById(application.getIdAnnouncement());
                    applicationsResponse.setAnnouncement(announcementResponse);

                    return applicationsResponse;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ApplicationsResponse> getApplicationsByStatus(ApplicationStatus status) {
        return applicationsRepository.findByStatus(status)
                .stream()
                .map(application -> {
                    ApplicationsResponse applicationsResponse = applicationsMapper.toApplicationsResponse(application);

                    AnnouncementResponse announcementResponse = announcementService.getAnnouncementById(application.getIdAnnouncement());
                    applicationsResponse.setAnnouncement(announcementResponse);

                    return applicationsResponse;
                })
                .collect(Collectors.toList());
    }

    private String generateFolio() {
        LocalDateTime now = LocalDateTime.now();
        return "SUBE-" + now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
    }

}
