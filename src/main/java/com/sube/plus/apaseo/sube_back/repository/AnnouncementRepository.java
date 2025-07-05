package com.sube.plus.apaseo.sube_back.repository;

import com.sube.plus.apaseo.sube_back.model.Announcement;
import com.sube.plus.apaseo.sube_back.model.enums.AnnouncementStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnnouncementRepository extends MongoRepository<Announcement, String> {

    Optional<Announcement> findById(String Id);

    Optional<Announcement> findByIdProgramAndAnnouncementStatus(String idProgram, AnnouncementStatus status);}