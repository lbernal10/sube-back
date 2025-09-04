package com.sube.plus.apaseo.sube_back.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sube.plus.apaseo.sube_back.model.enums.Gender;
import com.sube.plus.apaseo.sube_back.model.enums.MaritalStatus;
import com.sube.plus.apaseo.sube_back.model.enums.RelationshipToApplicant;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TutorResponse {

    private String name;
    private String lastNameP;
    private String lastNameM;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    private String curp;
    private Gender gender;
    private RelationshipToApplicant relationshipToApplicant;
    private MaritalStatus maritalStatus;

}