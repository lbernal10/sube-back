package com.sube.plus.apaseo.sube_back.model.request;

import com.sube.plus.apaseo.sube_back.model.enums.DocumentType;
import com.sube.plus.apaseo.sube_back.model.enums.TypeDocumentProgram;
import lombok.*;

@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DocumentProgramRequest {

    private String name;

    private String description;

    private DocumentType documentType;

    private Boolean requireTemplate;

    private String templateId;

    private TypeDocumentProgram typeDocumentProgram;

}
