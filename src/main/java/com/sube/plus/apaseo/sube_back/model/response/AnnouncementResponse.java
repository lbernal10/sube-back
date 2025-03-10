package com.sube.plus.apaseo.sube_back.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sube.plus.apaseo.sube_back.model.DocumentAnnouncement;
import com.sube.plus.apaseo.sube_back.model.enums.AnnouncementStatus;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AnnouncementResponse {

    private String id;

    private ProgramResponse program;

    private String name;

    private String description;

    private Integer beneficiaries;

    private List<DocumentAnnouncement> applicationDocument;

    private List<DocumentAnnouncement> specificationDocument;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateStart;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateFinish;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt; // 2024-11-01T00:00:00.000+00:00

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime  updatedAt; // 2024-11-01T00:00:00.000+00:00

    private AnnouncementStatus announcementStatus;
}
