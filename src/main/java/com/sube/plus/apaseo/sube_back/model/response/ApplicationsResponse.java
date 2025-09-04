package com.sube.plus.apaseo.sube_back.model.response;

import com.sube.plus.apaseo.sube_back.model.DocumentApplications;
import com.sube.plus.apaseo.sube_back.model.enums.ApplicationStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationsResponse {

    private String id;
    private String idAnnouncement;
    private SocioEconomicResponse socioEconomic;
    private TutorResponse tutor;
    private SchoolDataResponse schoolData;
    private String userId;
    private String folio;
    private ApplicationStatus status;
    private String statusReason;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean activeSupport;
    private boolean juveCardDelivered;
    private List<DocumentApplications> document;

}