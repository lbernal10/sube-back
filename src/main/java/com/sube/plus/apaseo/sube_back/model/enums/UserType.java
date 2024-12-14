package com.sube.plus.apaseo.sube_back.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum representing the type of user in the system.
 */
@AllArgsConstructor
@Getter
public enum UserType {
    /**
     * Administrator with full access and control over the system.
     */
    ADMIN("Administrador/a"),

    /**
     * Reviewer responsible for evaluating and approving requests.
     */
    REVIEWER("Revisor/a"),

    /**
     * Applicant who submits requests to the system.
     */
    APPLICANT("Aplicante");

    private String description;
}