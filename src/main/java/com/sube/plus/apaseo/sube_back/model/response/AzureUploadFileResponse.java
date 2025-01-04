package com.sube.plus.apaseo.sube_back.model.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AzureUploadFileResponse {

    private String name;

    private String url;
}
