package com.sube.plus.apaseo.sube_back.model.response;

import com.sube.plus.apaseo.sube_back.model.DocumentProgram;
import com.sube.plus.apaseo.sube_back.model.enums.ProgramStatus;
import com.sube.plus.apaseo.sube_back.model.enums.SupportType;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProgramResponse implements Serializable {

    private String id;

    private String name;

    private String description;

    private SupportType supportType;

    private Boolean requireEvidence;

    private Boolean socioEconomicStudy;

    private Boolean compatibilityWithOtherPrograms;

    private Boolean juveCard;

    private ProgramStatus programStatus;

    private List<DocumentProgram> document;

}
