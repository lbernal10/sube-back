package com.sube.plus.apaseo.sube_back.model;

import com.sube.plus.apaseo.sube_back.model.enums.Gender;
import com.sube.plus.apaseo.sube_back.model.enums.MaritalStatus;
import com.sube.plus.apaseo.sube_back.model.enums.RelationshipToApplicant;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Document
public class Tutor {

    private String name; // Luis Francisco

    private String lastNameP; // Bernal

    private String lastNameM; // Rios

    private Date dateOfBirth; // 1995-06-10

    private String curp; // BERL950610HGTRSS03

    private Gender gender; // [MALE] or [FEMALE]

    private RelationshipToApplicant relationshipToApplicant;

    private MaritalStatus maritalStatus;

}
