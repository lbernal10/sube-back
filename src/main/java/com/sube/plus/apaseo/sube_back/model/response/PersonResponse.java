package com.sube.plus.apaseo.sube_back.model.response;

import com.sube.plus.apaseo.model.enums.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PersonResponse {

    private String id;

    private String name; // Luis Francisco

    private String lastNameP; // Bernal

    private String lastNameM; // Rios

    private Date dateOfBirth; // 1995-06-10

    private AddressResponse address;

    private String curp; // BERL950610HGTRSS03

    private String rfc; // BERL950610I43

    private Gender gender; // [MALE] or [FEMALE]

    private Boolean disability; // true or false

    private MaritalStatus maritalStatus;

    private Boolean indigenousGroup; // true or false

    private Boolean motherOrPregnent; // true or false

    private InmigrationStatus inmigrationStatus;

    private AddressResponse addressHome; // revisar bien el nombre

    private SettlementType settlementType;

    private RoadType roadType;

    private PathType pathType;

}
