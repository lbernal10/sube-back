package com.sube.plus.apaseo.sube_back.model.request;

import com.sube.plus.apaseo.sube_back.model.enums.EducationLevel;
import com.sube.plus.apaseo.sube_back.model.enums.SchoolPeriod;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchoolDataRequestDTO {

    private EducationLevel educationLevel;

    private String schoolName;

    private String cct;

    private String major;

    private String grade;

    private Integer gpa;

    private SchoolPeriod schoolPeriod;

}