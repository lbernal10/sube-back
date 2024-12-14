package com.sube.plus.apaseo.sube_back.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum representing marital status options.
 */
@AllArgsConstructor
@Getter
public enum MaritalStatus {

    SINGLE("Soltero/a"),
    MARRIED("Casado/a"),
    DIVORCED("Divorciado/a"),
    WIDOWED("Viudo/a"),
    SEPARATED("Separado/a legalmente"),
    ENGAGED("Comprometido/a"),
    COHABITING("Conviviente o en unión libre"),
    UNKNOWN("Estado civil no especificado");

    private String description;
}