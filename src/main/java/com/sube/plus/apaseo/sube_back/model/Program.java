package com.sube.plus.apaseo.sube_back.model;

import com.sube.plus.apaseo.sube_back.model.enums.ProgramStatus;
import com.sube.plus.apaseo.sube_back.model.enums.SupportType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
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

    @Builder.Default
    private Boolean requireEvidence = false;

    @Builder.Default
    private Boolean socioEconomicStudy = false;

    @Builder.Default
    private Boolean compatibilityWithOtherPrograms = false;

    @Builder.Default
    private Boolean juveCard = false;

    private ProgramStatus programStatus;

    private List<DocumentProgram> document;

    private LocalDateTime createdAt; // 2024-11-01T00:00:00.000+00:00

}
