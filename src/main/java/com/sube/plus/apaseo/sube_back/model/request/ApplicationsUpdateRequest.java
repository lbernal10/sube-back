package com.sube.plus.apaseo.sube_back.model.request;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.sube.plus.apaseo.sube_back.model.DocumentApplications;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplicationsUpdateRequest {

    private SocioEconomicRequest socioEconomic;

    private TutorRequest tutor;

    private SchoolDataRequest schoolData;


}
