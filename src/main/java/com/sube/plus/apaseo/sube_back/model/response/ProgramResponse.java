package com.sube.plus.apaseo.sube_back.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.sube.plus.apaseo.sube_back.model.DocumentProgram;
import com.sube.plus.apaseo.sube_back.model.enums.ProgramStatus;
import com.sube.plus.apaseo.sube_back.model.enums.SupportType;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProgramResponse implements Serializable {

    private String id;

    private String name;

    private String description;

    private SupportType supportType;

    private Boolean requireEvidence;

    private Boolean socioEconomicStudy;

    private Boolean compatibilityWithOtherPrograms;

    private Boolean juveCard;

    private ProgramStatus programStatus;

    private List<DocumentProgram> document;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt; // 2024-11-01T00:00:00.000+00:00

}
