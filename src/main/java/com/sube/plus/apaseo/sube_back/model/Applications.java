package com.sube.plus.apaseo.sube_back.model;

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
public class Applications {

    @Id
    private String id;
    
    private String idAnnouncement;

    private String idUser;

}
