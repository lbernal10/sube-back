package com.sube.plus.apaseo.sube_back.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum representing the type of path.
 */
@AllArgsConstructor
@Getter
public enum PathType {
    /**
     * Paved path suitable for vehicles.
     */
    PAVED("Pavimento"),

    /**
     * Dirt path, often found in rural or undeveloped areas.
     */
    DIRT("Tierra"),

    /**
     * Gravel path, typically made with small stones.
     */
    GRAVEL("Grava");

    private String description;
}