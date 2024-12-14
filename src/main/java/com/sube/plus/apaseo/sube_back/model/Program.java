package com.sube.plus.apaseo.sube_back.model;

import com.sube.plus.apaseo.model.enums.ProgramStatus;
import com.sube.plus.apaseo.model.enums.SupportType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Document
public class Program {

    @Id
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
