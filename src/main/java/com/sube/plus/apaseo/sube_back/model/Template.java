package com.sube.plus.apaseo.sube_back.model;

import com.sube.plus.apaseo.sube_back.model.enums.TemplateStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Document
public class Template {

    @Id
    private String id;

    private String name;

    private String fileName;

    private String url;

    private TemplateStatus status;

}
