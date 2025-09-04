package com.sube.plus.apaseo.sube_back.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum representing the status of a user.
 */
@AllArgsConstructor
@Getter
public enum DocumentApplicationStatus {

    PENDING_REVIEW("Pendiente de revisar"),

    APPROVED("Aceptado"),

    REJECTED("Rechazado");

    private String description;

}