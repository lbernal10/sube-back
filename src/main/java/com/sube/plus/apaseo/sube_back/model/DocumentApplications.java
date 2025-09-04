package com.sube.plus.apaseo.sube_back.model;

import com.sube.plus.apaseo.sube_back.model.enums.DocumentApplicationStatus;
import com.sube.plus.apaseo.sube_back.model.enums.DocumentApplicationType;
import com.sube.plus.apaseo.sube_back.model.enums.DocumentType;
import lombok.*;

import java.io.Serializable;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DocumentApplications implements Serializable {

    private String name;

    private String description;

    private DocumentType documentType;

    @Builder.Default
    private Boolean requireTemplate = false;

    private String templateId;

    private DocumentApplicationType type;// Solcitud o Seguimiento  - DocumentApplicationType y TypeDocumentProgram son lo mismo, hay que unificarlo

    private DocumentApplicationStatus status;// Pendiente de revisar, aceptado o rechazado

    private String rejectionReason; // Raz[on por la que se rechazo el documento

}
