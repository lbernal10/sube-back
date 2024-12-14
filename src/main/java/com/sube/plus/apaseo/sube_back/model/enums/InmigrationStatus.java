package com.sube.plus.apaseo.sube_back.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum inmigration status options.
 */
@AllArgsConstructor
@Getter
public enum InmigrationStatus {

    IMMIGRANT("Inmigrante"),
    REFUGEE("Refugiado"),
    DISPLACED("Desplazado"),
    NONE_OF_THE_ABOVE("Ninguna de las anteriores");

    private String description;
}