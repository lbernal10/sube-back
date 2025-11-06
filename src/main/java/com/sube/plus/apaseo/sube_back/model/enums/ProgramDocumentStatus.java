package com.sube.plus.apaseo.sube_back.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum ProgramDocumentStatus {

    ACTIVE("Activo/a"),
    INACTIVE("Inactivo/a");

    private String description;
}
