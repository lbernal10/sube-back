package com.sube.plus.apaseo.sube_back.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum ProgramStatus {

    ACTIVE("Activo/a"),
    INACTIVE("Inactivo/a");

    private String description;
}
