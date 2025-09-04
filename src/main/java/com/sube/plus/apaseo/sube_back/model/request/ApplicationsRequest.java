package com.sube.plus.apaseo.sube_back.model.request;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.sube.plus.apaseo.sube_back.model.DocumentApplications;
import com.sube.plus.apaseo.sube_back.model.enums.ApplicationStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplicationsRequest {

    private String idAnnouncement;
    private SocioEconomicRequest socioEconomic;
    private TutorRequest tutor;
    private SchoolDataRequest schoolData;
    private String userId;
    private boolean activeSupport;
    private boolean juveCardDelivered;
    private List<DocumentApplications> document;

}
