package com.sube.plus.apaseo.sube_back.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum representing the status of a user.
 */
@AllArgsConstructor
@Getter
public enum DocumentApplicationType {

    REQUESTED("Solicitado"),

    EVIDENCE("Evidencia");

    private String description;

}