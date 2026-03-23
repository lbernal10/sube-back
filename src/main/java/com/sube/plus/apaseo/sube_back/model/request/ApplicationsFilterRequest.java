package com.sube.plus.apaseo.sube_back.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sube.plus.apaseo.sube_back.model.enums.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplicationsFilterRequest {

    private String folio;
    private ApplicationStatus status;
    private String idAnnouncement;
    private String idProgram;
    private String applicantName;
    private String curp;
    private LocalDate requestDateFrom;
    private LocalDate requestDateTo;

}
