package com.sube.plus.apaseo.sube_back.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum RegimenTenenciaVivienda {

    OWNER("Propietario"),
    TENANT("Arrendatario"),
    USUFRUCT_OR_GRANTED("Usufructo / Cedido"),
    NO_TITLE_POSSESSION("Posesión sin título (ocupación irregular)"),
    CONDOMINIUM_OR_COOPERATIVE("Propiedad en condominio o cooperativa"),
    INSTITUTIONAL_HOUSING("Vivienda institucional");

    private String description;
}