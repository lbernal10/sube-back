package com.sube.plus.apaseo.sube_back.model;

import com.sube.plus.apaseo.sube_back.model.enums.EducationLevel;
import com.sube.plus.apaseo.sube_back.model.enums.SchoolPeriod;
import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SchoolData {

    private EducationLevel educationLevel;

    private String schoolName;

    private String cct; // codigo ante la secretaria

    private String major;// Carrera

    private String grade;// Grado

    private Integer gpa;// Promedio

    private SchoolPeriod schoolPeriod;

}
