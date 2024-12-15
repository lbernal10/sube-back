package com.sube.plus.apaseo.sube_back.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum representing the status of a user.
 */
@AllArgsConstructor
@Getter
public enum UserStatus {
    /**
     * The user is pre-active and not yet fully activated.
     */
    PREACTIVE("Preactivo/a"),

    /**
     * The user is active and fully functional.
     */
    ACTIVE("Activo/a"),

    /**
     * The user is inactive and not currently in use.
     */
    INACTIVE("Inactivo/a");

    private String description;

    public boolean isActive() {
        return this == ACTIVE;
    }
}