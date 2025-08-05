package com.sube.plus.apaseo.sube_back.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DiseaseDetectionStatus {

    YES("Si"),
    NO("No"),
    NOT_APPLICABLE("No Aplica"),
    UNKNOWN("Desconocido");

    private String description;

}
