package com.sube.plus.apaseo.sube_back.service.impl;

import com.sube.plus.apaseo.sube_back.converter.AnnouncementMapper;
import com.sube.plus.apaseo.sube_back.model.Announcement;
import com.sube.plus.apaseo.sube_back.model.DocumentAnnouncement;
import com.sube.plus.apaseo.sube_back.model.enums.AnnouncementStatus;
import com.sube.plus.apaseo.sube_back.model.response.AnnouncementResponse;
import com.sube.plus.apaseo.sube_back.model.response.AzureUploadFileResponse;
import com.sube.plus.apaseo.sube_back.model.response.ProgramResponse;
import com.sube.plus.apaseo.sube_back.repository.AnnouncementRepository;
import com.sube.plus.apaseo.sube_back.service.AzureBlobStorageService;
import com.sube.plus.apaseo.sube_back.service.AnnouncementService;
import com.sube.plus.apaseo.sube_back.service.ProgramService;
import com.sube.plus.apaseo.sube_back.util.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementRepository announcementRepository;
    private final AnnouncementMapper announcementMapper;
    private final AzureBlobStorageService azureBlobStorageService;

    private final ProgramService programService;

    @Override
    public List<AnnouncementResponse> getAllAnnouncements() {
        return announcementRepository.findAll()
                .stream()
                .filter(a -> AnnouncementStatus.ACTIVE.equals(a.getAnnouncementStatus())) // Filtramos los anuncios activos
                .map(announcement -> {
                    AnnouncementResponse announcementResponse = announcementMapper.toAnnouncementResponse(announcement); // Convertimos el anuncio
                    ProgramResponse programResponse = programService.getProgramById(announcement.getIdProgram()); // Obtenemos el programa asociado
                    announcementResponse.setProgram(programResponse); // Establecemos el programa en la respuesta del anuncio
                    return announcementResponse; // Devolvemos la respuesta con el programa agregado
                })
                .collect(Collectors.toList()); // Recopilamos todos los resultados en una lista
    }

    @Override
    public AnnouncementResponse getAnnouncementById(String id) {
        Announcement announcement = announcementRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Announcement not found with id: " + id));

        AnnouncementResponse announcementResponse = announcementMapper.toAnnouncementResponse(announcement);
        ProgramResponse programResponse = programService.getProgramById(announcement.getIdProgram());
        announcementResponse.setProgram(programResponse);

        return announcementResponse;
    }

    @Override
    public AnnouncementResponse createAnnouncement(String idProgram, String name, String description, Integer beneficiaries, Date dateStart, Date dateFinish, MultipartFile applicationDocument, MultipartFile specificationDocument) throws IOException {
        AzureUploadFileResponse applicationDocumentFileAzure = azureBlobStorageService.uploadFile(applicationDocument);
        AzureUploadFileResponse specificationDocumentFileAzure = azureBlobStorageService.uploadFile(specificationDocument);

        DocumentAnnouncement applicationDocumentFile = DocumentAnnouncement.builder()
                .name("Detalle Convocatoria")
                .fileName(applicationDocumentFileAzure.getName())
                .uri(applicationDocumentFileAzure.getUrl())
            .build();

        List<DocumentAnnouncement> applicationDocumentFileList = Collections.singletonList(applicationDocumentFile);

        DocumentAnnouncement specificationDocumentFileAzureFile = DocumentAnnouncement.builder()
                .name("Especificaciones del Programa")
                .fileName(specificationDocumentFileAzure.getName())
                .uri(specificationDocumentFileAzure.getUrl())
                .build();

        List<DocumentAnnouncement> specificationDocumentFileAzureFileList = Collections.singletonList(specificationDocumentFileAzureFile);

        Announcement announcement = Announcement.builder()
                .idProgram(idProgram)
                .name(name)
                .description(description)
                .beneficiaries(beneficiaries)
                .applicationDocument(applicationDocumentFileList)
                .specificationDocument(specificationDocumentFileAzureFileList)
                .dateStart(dateStart)
                .dateFinish(dateFinish)
                .createdAt(LocalDateTime.now())
                .updatedAt(null)
                .announcementStatus(AnnouncementStatus.ACTIVE)
                .build();

        AnnouncementResponse announcementResponse = announcementMapper.toAnnouncementResponse(announcementRepository.save(announcement));

        ProgramResponse programResponse = programService.getProgramById(announcement.getIdProgram());
        announcementResponse.setProgram(programResponse);

        return announcementResponse;
    }

    @Override
    public AnnouncementResponse updateAnnouncement(String id, String idProgram, String name, String description, Integer beneficiaries, Date dateStart, Date dateFinish, MultipartFile applicationDocument, MultipartFile specificationDocument) throws IOException {
        // Buscar el anuncio existente
        Announcement existingAnnouncement = announcementRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Announcement not found with id: " + id));

        // Subir nuevos archivos si se proporcionan
        List<DocumentAnnouncement> applicationDocumentFileList = existingAnnouncement.getApplicationDocument();
        if (applicationDocument != null) {
            AzureUploadFileResponse applicationDocumentFileAzure = azureBlobStorageService.uploadFile(applicationDocument);
            DocumentAnnouncement applicationDocumentFile = DocumentAnnouncement.builder()
                    .name("Detalle Convocatoria")
                    .fileName(applicationDocumentFileAzure.getName())
                    .uri(applicationDocumentFileAzure.getName())
                    .build();
            applicationDocumentFileList = Collections.singletonList(applicationDocumentFile);
        }

        List<DocumentAnnouncement> specificationDocumentFileList = existingAnnouncement.getSpecificationDocument();
        if (specificationDocument != null) {
            AzureUploadFileResponse specificationDocumentFileAzure = azureBlobStorageService.uploadFile(specificationDocument);
            DocumentAnnouncement specificationDocumentFile = DocumentAnnouncement.builder()
                    .name("Especificaciones del Programa")
                    .fileName(specificationDocumentFileAzure.getName())
                    .uri(specificationDocumentFileAzure.getName())
                    .build();
            specificationDocumentFileList = Collections.singletonList(specificationDocumentFile);
        }

        // Actualizar el anuncio con los nuevos valores
        existingAnnouncement.setName(name);
        existingAnnouncement.setIdProgram(idProgram);
        existingAnnouncement.setDescription(description);
        existingAnnouncement.setBeneficiaries(beneficiaries);
        existingAnnouncement.setApplicationDocument(applicationDocumentFileList);
        existingAnnouncement.setSpecificationDocument(specificationDocumentFileList);
        existingAnnouncement.setDateStart(dateStart);
        existingAnnouncement.setDateFinish(dateFinish);
        existingAnnouncement.setUpdatedAt(LocalDateTime.now());

        // Guardar y retornar el anuncio actualizado
        AnnouncementResponse announcementResponse = announcementMapper.toAnnouncementResponse(announcementRepository.save(existingAnnouncement));

        ProgramResponse programResponse = programService.getProgramById(existingAnnouncement.getIdProgram());
        announcementResponse.setProgram(programResponse);

        return announcementResponse;
    }

    @Override
    public AnnouncementResponse deleteAnnouncement(String id) {
        Announcement announcement = announcementRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Announcement not found with id: " + id));

        announcement.setAnnouncementStatus(AnnouncementStatus.INACTIVE);

        AnnouncementResponse announcementResponse = announcementMapper.toAnnouncementResponse(announcementRepository.save(announcement));

        ProgramResponse programResponse = programService.getProgramById(announcement.getIdProgram());
        announcementResponse.setProgram(programResponse);

        return announcementResponse;
    }

    @Override
    public List<AnnouncementResponse> getAnnouncementByIdProgram(String idProgram) {
        List<Announcement> announcements = announcementRepository
                .findByIdProgramAndAnnouncementStatus(idProgram, AnnouncementStatus.ACTIVE);

        if (announcements.isEmpty()) {
            throw new NotFoundException("No active announcements found with idProgram: " + idProgram);
        }

        return announcements.stream().map(announcement -> {
            AnnouncementResponse response = announcementMapper.toAnnouncementResponse(announcement);
            ProgramResponse programResponse = programService.getProgramById(announcement.getIdProgram());
            response.setProgram(programResponse);
            return response;
        }).collect(Collectors.toList());
    }
}