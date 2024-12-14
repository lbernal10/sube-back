package com.sube.plus.apaseo.sube_back.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum representing the gender of an individual.
 */
@AllArgsConstructor
@Getter
public enum Gender {
    MALE("Masculino"),
    FEMALE("Femenino");

    private String description;
}
