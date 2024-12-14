package com.sube.plus.apaseo.sube_back.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum representing the type of road.
 */

@AllArgsConstructor
@Getter
public enum RoadType {

    STREET("Calle"),
    BOULEVARD("Boulevard");

    private String description;
}