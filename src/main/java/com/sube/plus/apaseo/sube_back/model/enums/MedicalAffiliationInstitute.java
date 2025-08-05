package com.sube.plus.apaseo.sube_back.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MedicalAffiliationInstitute {

    IMSS("Instituto Mexicano del Seguro Social"),
    ISSSTE("Instituto de Seguridad y Servicios Sociales de los Trabajadores del Estado"),
    PEMEX("Servicios Médicos de Petróleos Mexicanos"),
    SEDENA("Secretaría de la Defensa Nacional - Servicios Médicos"),
    SEMAR("Secretaría de Marina - Servicios Médicos"),
    INSABI("Instituto de Salud para el Bienestar"),
    PRIVATE("Servicios Médicos Privados"),
    NONE("Sin Afiliación");

    private final String descripcion;

}
