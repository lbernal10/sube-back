package com.sube.plus.apaseo.sube_back.model;

import com.sube.plus.apaseo.model.enums.AnnouncementStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
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
public class Announcement {

    @Id
    private String id;

    private String idProgram;

    private String name;

    private Integer beneficiaries;

    private List<DocumentAnnouncement> documentAnnouncements;

    private Date dateStart;

    private Date dateFinish;

    private LocalDate createdAt; // 2024-11-01T00:00:00.000+00:00

    private LocalDate updatedAt; // 2024-11-01T00:00:00.000+00:00

    private AnnouncementStatus announcementStatus;

}
