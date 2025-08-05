package com.sube.plus.apaseo.sube_back.model;

import com.sube.plus.apaseo.sube_back.model.enums.AnnouncementStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Document
/**
 * Convocatoria
 */
public class Announcement {

    @Id
    private String id;

    private String idProgram;

    private String name;

    private String description;

    private Integer beneficiaries;

    private List<DocumentAnnouncement> applicationDocument;

    private List<DocumentAnnouncement> specificationDocument;

    private Date dateStart;

    private Date dateFinish;

    private LocalDateTime createdAt; // 2024-11-01T00:00:00.000+00:00

    private LocalDateTime updatedAt; // 2024-11-01T00:00:00.000+00:00

    private AnnouncementStatus announcementStatus;

}
