package com.sube.plus.apaseo.sube_back.model;

import com.sube.plus.apaseo.sube_back.model.enums.DocumentType;
import com.sube.plus.apaseo.sube_back.model.enums.TypeDocumentProgram;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DocumentProgram implements Serializable {

    @Builder.Default
    private String id = UUID.randomUUID().toString();

    private String name;

    private String description;

    private DocumentType documentType;

    @Builder.Default
    private Boolean requireTemplate = false;

    private String templateId;

    private TypeDocumentProgram typeDocumentProgram;

}
