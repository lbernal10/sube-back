package com.sube.plus.apaseo.sube_back.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum representing the type of support for scholarships.
 */
@AllArgsConstructor
@Getter
public enum SupportType {

    A("Economico"),
    B("Equipo de computo o laptop");

    private String description;
}