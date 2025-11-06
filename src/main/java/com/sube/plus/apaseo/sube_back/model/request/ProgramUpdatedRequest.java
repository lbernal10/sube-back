package com.sube.plus.apaseo.sube_back.model.request;

import com.sube.plus.apaseo.sube_back.model.enums.ProgramStatus;
import com.sube.plus.apaseo.sube_back.model.enums.SupportType;
import lombok.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;

@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProgramUpdatedRequest implements Serializable {

    private String id;

    private String name;

    private String description;

    private SupportType supportType;

    private Boolean requireEvidence;

    private Boolean socioEconomicStudy;

    private Boolean compatibilityWithOtherPrograms;

    private Boolean juveCard;

    private ProgramStatus programStatus;

    private List<DocumentProgramUpdateRequest> document;

    private ZonedDateTime createdAt; // 2024-11-01T00:00:00.000+00:00

}
