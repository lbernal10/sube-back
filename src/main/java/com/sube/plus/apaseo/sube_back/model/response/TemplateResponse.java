package com.sube.plus.apaseo.sube_back.model.response;

import com.sube.plus.apaseo.sube_back.model.enums.TemplateStatus;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TemplateResponse {

    private String id;

    private String name;

    private String fileName;

    private String url;

    private TemplateStatus status;

}
