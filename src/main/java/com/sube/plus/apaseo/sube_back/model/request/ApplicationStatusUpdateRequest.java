package com.sube.plus.apaseo.sube_back.model.request;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.sube.plus.apaseo.sube_back.model.enums.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplicationStatusUpdateRequest {

    private ApplicationStatus status;

    private String statusReason;
}
