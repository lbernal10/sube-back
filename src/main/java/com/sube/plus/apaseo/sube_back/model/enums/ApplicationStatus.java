package com.sube.plus.apaseo.sube_back.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ApplicationStatus {

    SENT("Enviada"),
    UNDER_REVIEW("En Revisión"),
    APPROVED("Aprobada"),
    OBSERVATIONS("Observaciones"),
    REJECTED("Rechazada"),
    CANCELED("Cancelada");

    private final String descripcion;
}
