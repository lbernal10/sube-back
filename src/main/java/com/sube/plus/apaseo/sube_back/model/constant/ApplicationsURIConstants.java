package com.sube.plus.apaseo.sube_back.model.constant;

public final class ApplicationsURIConstants {

    public static final String APPLICATIONS = "/applications";

    public static final String APPLICATIONS_BY_ID = APPLICATIONS + "/{id}";

    // Document endpoints
    public static final String DOCUMENTS_BY_APPLICATION_AND_DOCUMENT_ID =
            APPLICATIONS + "/{applicationId}/documents/{documentId}";

    public static final String DOCUMENTS_APPROVE =
            DOCUMENTS_BY_APPLICATION_AND_DOCUMENT_ID + "/approve";

    public static final String DOCUMENTS_REJECT =
            DOCUMENTS_BY_APPLICATION_AND_DOCUMENT_ID + "/reject";


    // Rutas adicionales
    public static final String APPLICATIONS_BY_USER_ID =  APPLICATIONS + "/user/{userId}";
    public static final String APPLICATIONS_BY_STATUS =  APPLICATIONS + "/status/{status}";
}
