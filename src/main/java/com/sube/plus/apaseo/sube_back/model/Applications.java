package com.sube.plus.apaseo.sube_back.model;

import com.sube.plus.apaseo.sube_back.model.enums.ApplicationStatus;
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
/**
 * Solicitudes
 *
 * Solicitante: Crear solicitud,, cancelar su misma solcitud (sienmpre y cuanbdo este en estatus de En Revisión, subir documentos (tipo evidencias), corregir documentos y se pone el estatus de documento en Pendiente de revisar, )
 *
 * Revisor: Pendiente para la siguiente fase
 *
 */
public class Applications {

    @Id
    private String id;

    private String idAnnouncement;// Convocatoria

    private String idSocioEconomic; // Socio Economico

    private String idTutor;

    private String idSchoolData;

    private String userId;

    private String folio;// libre de generarlo el formato

    private ApplicationStatus status;

    private String statusReason;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private boolean activeSupport;

    private boolean juveCardDelivered;

    private List<DocumentApplications> document;

}
