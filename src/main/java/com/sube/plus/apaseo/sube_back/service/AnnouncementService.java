package com.sube.plus.apaseo.sube_back.service;

import com.sube.plus.apaseo.sube_back.model.response.AnnouncementResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface AnnouncementService {
    List<AnnouncementResponse> getAllAnnouncements();

    AnnouncementResponse getAnnouncementById(String id);

    AnnouncementResponse createAnnouncement(String idProgram, String name, String description, Integer beneficiaries, Date dateStart, Date dateFinish, MultipartFile applicationDocument, MultipartFile specificationDocument) throws IOException;

    AnnouncementResponse updateAnnouncement(String id, String idProgram, String name, String description, Integer beneficiaries, Date dateStart, Date dateFinish, MultipartFile applicationDocument, MultipartFile specificationDocument) throws IOException;

    AnnouncementResponse deleteAnnouncement(String id);

    List<AnnouncementResponse> getAnnouncementByIdProgram(String idProgram);

}