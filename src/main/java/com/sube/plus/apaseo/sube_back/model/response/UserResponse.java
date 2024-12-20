package com.sube.plus.apaseo.sube_back.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.sube.plus.apaseo.sube_back.model.enums.UserStatus;
import com.sube.plus.apaseo.sube_back.model.enums.UserType;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserResponse  implements Serializable {

    private String email; // contactolbernal@gmail.com

    @JsonIgnore
    private String password; // Password encryp

    private UserStatus status; // [PREACTIVE], [ACTIVE], [INACTIVE]

    private UserType type; // [ADMIN], [REVIEWER] or [APPLICANT]

    private String personId;

    private String phone; // 4776286336

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt; // 2024-11-01T00:00:00.000+00:00

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate lastAccess; // 2024-11-01T00:00:00.000+00:00

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate updatedAt; // 2024-11-01T00:00:00.000+00:00

    private Boolean verifyEmail;

    private Boolean verifyPhone;

    @JsonIgnore
    private String verificationCodeEmail;

    @JsonIgnore
    private String verificationCodePhone;

    @JsonIgnore
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime verificationCodeEmailSentAt;

    @JsonIgnore
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime verificationCodePhoneSentAt;


}
