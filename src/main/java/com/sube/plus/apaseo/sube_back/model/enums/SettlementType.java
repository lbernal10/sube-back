package com.sube.plus.apaseo.sube_back.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum representing the type of settlement.
 */
@AllArgsConstructor
@Getter
public enum SettlementType {

    URBAN("Urbano"),

    RURAL("Rural");

    private String description;
}