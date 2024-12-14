package com.sube.plus.apaseo.sube_back.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DocumentType {

    PDF(".pdf"),
    WORD(".docx"),
    EXCEL(".xlsx");

    private String description;
}
