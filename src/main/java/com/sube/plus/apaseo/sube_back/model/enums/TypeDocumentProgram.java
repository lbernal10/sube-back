package com.sube.plus.apaseo.sube_back.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum TypeDocumentProgram {

    REQUESTED("Solicitado"),

    EVIDENCE("Evidencia");

    private String description;
}