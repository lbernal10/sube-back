package com.sube.plus.apaseo.sube_back.model;

import com.sube.plus.apaseo.sube_back.model.enums.DocumentType;
import com.sube.plus.apaseo.sube_back.model.enums.TypeDocumentProgram;
import lombok.*;

import java.io.Serializable;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DocumentProgram implements Serializable {

    private String name;

    private String description;

    private DocumentType documentType;

    @Builder.Default
    private Boolean requireTemplate = false;

    private String templateId;

    private TypeDocumentProgram typeDocumentProgram;

}
