package com.sube.plus.apaseo.sube_back.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EducationLevel {

    NONE("Sin estudios"),
    PRESCHOOL("Preescolar"),
    PRIMARY("Primaria"),
    SECONDARY("Secundaria"),
    HIGH_SCHOOL("Bachillerato / Preparatoria"),
    TECHNICAL("Carrera Técnica"),
    BACHELOR("Licenciatura"),
    POSTGRADUATE("Posgrado");

    private String description;

}
