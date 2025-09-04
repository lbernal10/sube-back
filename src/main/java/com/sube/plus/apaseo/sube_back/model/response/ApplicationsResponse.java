package com.sube.plus.apaseo.sube_back.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sube.plus.apaseo.sube_back.model.DocumentApplications;
import com.sube.plus.apaseo.sube_back.model.enums.ApplicationStatus;
import lombok.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationsResponse implements Serializable {

    private String id;
    private String idAnnouncement;
    private SocioEconomicResponse socioEconomic;
    private TutorResponse tutor;
    private SchoolDataResponse schoolData;
    private String userId;
    private String folio;
    private ApplicationStatus status;
    private String statusReason;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX",  timezone = "America/Mexico_City")
    private ZonedDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX",  timezone = "America/Mexico_City")
    private ZonedDateTime updatedAt;
    private boolean activeSupport;
    private boolean juveCardDelivered;
    private List<DocumentApplications> document;

}