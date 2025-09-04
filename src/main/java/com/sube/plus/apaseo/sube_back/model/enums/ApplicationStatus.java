package com.sube.plus.apaseo.sube_back.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ApplicationStatus {

    UNDER_REVIEW("En Revisión"),
    APPROVED("Aprobada"),
    RETURNED("Devuelta (con observaciones)"),
    REJECTED("Rechazada"),
    CANCELED("Cancelada");

    private final String descripcion;
}
