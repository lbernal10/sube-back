package com.sube.plus.apaseo.sube_back.model.request;

import com.sube.plus.apaseo.sube_back.model.enums.UserStatus;
import com.sube.plus.apaseo.sube_back.model.enums.UserType;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ReviewerRequest {

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

    private String numEmployee;

}
