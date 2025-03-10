package com.sube.plus.apaseo.sube_back.model.constant;

public final class UserURIConstants {

    public static final String USER = "/user";

    public static final String USER_BY_ID = USER + "/{id}";

    public static final String APPLICANT = "/applicant";
    public static final String REVIEWER = "/reviewer";

    public static final String USER_APPLICANT = USER + APPLICANT;

    public static final String USER_REVIEWER = USER + REVIEWER;

    public static final String USER_VALIDATE_EMAIL = USER + "/validate/email/{id}/{verificationCodeEmail}";

    public static final String USER_VALIDATE_PHONE = USER + "/validate/phone/{id}/{verificationCodePhone}";

    public static final String SEND_USER_VALIDATE_EMAIL = USER + "/send/email/{id}/{email}";

    public static final String SEND_USER_VALIDATE_PHONE = USER + "/send/phone/{id}/{phone}";

    public static final String SEND_USER_PASSWORD = USER + "/send/password/{id}/{email}";

    public static final String USER_APPLICANT_BY_ID = USER_APPLICANT + "/{id}";

    public static final String USER_REVIEWER_BY_ID = USER_REVIEWER + "/{id}";

    public static final String SEND_CODE_RESET_PASSWORD = USER + "/send/reset";
    public static final String VALIDATE_CODE_RESET_PASSWORD = USER + "/validate/reset";

    public static final String RESET_PASSWORD = USER + "/reset";

    public static final String UPDATED_USER_VALIDATE_EMAIL = USER + "/update/email/{id}/{email}/{verificationCodeEmail}";

    public static final String UPDATED_USER_VALIDATE_PHONE = USER + "/update/phone/{id}/{phone}/{verificationCodePhone}";

    public static final String USER_REVIEWER_ACTIVE = USER + REVIEWER + "/active";
}
