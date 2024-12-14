package com.sube.plus.apaseo.sube_back.model;

import com.sube.plus.apaseo.sube_back.model.enums.DocumentType;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DocumentProgram {

    private String name;

    private DocumentType documentType;

    private Boolean requireTemplate;

    private String template; // Subir al storage la plantille, cambiar el tipo por que no es string

}
