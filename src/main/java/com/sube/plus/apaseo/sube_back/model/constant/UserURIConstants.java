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

}
