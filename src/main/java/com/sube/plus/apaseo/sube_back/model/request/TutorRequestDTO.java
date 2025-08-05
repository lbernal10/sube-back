package com.sube.plus.apaseo.sube_back.model.request;

import com.sube.plus.apaseo.sube_back.model.enums.Gender;
import com.sube.plus.apaseo.sube_back.model.enums.MaritalStatus;
import com.sube.plus.apaseo.sube_back.model.enums.RelationshipToApplicant;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TutorRequestDTO {

    private String name;
    private String lastNameP;
    private String lastNameM;
    private Date dateOfBirth;
    private String curp;
    private Gender gender;
    private RelationshipToApplicant relationshipToApplicant;
    private MaritalStatus maritalStatus;

}
