package com.sube.plus.apaseo.sube_back.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SchoolPeriod {

    SEMESTER("Semestre"),
    TRIMESTER("Trimestre"),
    QUARTER("Quatrimestre"),
    ANNUAL("Anual"),
    OTHER("Otro");

    private final String description;
}
