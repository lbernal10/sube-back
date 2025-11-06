package com.sube.plus.apaseo.sube_back.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sube.plus.apaseo.sube_back.model.enums.DocumentType;
import com.sube.plus.apaseo.sube_back.model.enums.ProgramDocumentStatus;
import com.sube.plus.apaseo.sube_back.model.enums.TypeDocumentProgram;
import lombok.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DocumentProgramUpdateRequest {

    private String id;

    private String name;

    private String description;

    private DocumentType documentType;

    private Boolean requireTemplate;

    private String templateId;

    private TypeDocumentProgram typeDocumentProgram;

    private ProgramDocumentStatus programDocumentStatus;

    @Builder.Default
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX",  timezone = "America/Mexico_City")
    private ZonedDateTime updatedAt = ZonedDateTime.now(ZoneId.of("Etc/GMT+6"));

}
