package com.sube.plus.apaseo.sube_back.model;

import com.sube.plus.apaseo.sube_back.model.enums.UserStatus;
import com.sube.plus.apaseo.sube_back.model.enums.UserType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Document
public class User {

    @Id
    private String id;

    private String email; // contactolbernal@gmail.com

    private String password; // Password encryp

    private UserStatus status; // [PREACTIVE], [ACTIVE], [INACTIVE]

    private UserType type; // [ADMIN], [REVIEWER] or [APPLICANT]

    private String personId;

    private String phone; // 4776286336

    private LocalDate createdAt; // 2024-11-01T00:00:00.000+00:00

    private LocalDate lastAccess; // 2024-11-01T00:00:00.000+00:00

    private LocalDate updatedAt; // 2024-11-01T00:00:00.000+00:00

    private Boolean verifyEmail;

    private Boolean verifyPhone;

    private String verificationCodeEmail;

    private String verificationCodePhone;

    private LocalDateTime verificationCodeEmailSentAt;

    private LocalDateTime verificationCodePhoneSentAt;

    private String resetCodePassword;

    private LocalDateTime resetCodePasswordSentAt;

    @Builder.Default
    private Boolean verifyResetCodePassword = false;

}
