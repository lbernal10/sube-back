package com.sube.plus.apaseo.sube_back.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RelationshipToApplicant {

    FATHER("Padre"),
    MOTHER("Madre"),
    SIBLING("Hermano(a)"),
    GRANDPARENT("Abuelo(a)"),
    TUTOR("Tutor(a)"),
    COMPANION("Acompañante"),
    AUTHORIZED_PERSON(" Persona Autorizada"),
    OTHER("Otro");

    private final String description;
}
