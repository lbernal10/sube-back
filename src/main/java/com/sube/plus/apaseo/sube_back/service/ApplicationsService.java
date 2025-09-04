package com.sube.plus.apaseo.sube_back.service;

import com.sube.plus.apaseo.sube_back.model.enums.ApplicationStatus;
import com.sube.plus.apaseo.sube_back.model.request.ApplicationsRequest;
import com.sube.plus.apaseo.sube_back.model.response.ApplicationsResponse;

import java.util.List;

public interface ApplicationsService {

    List<ApplicationsResponse> getAllApplications();

    ApplicationsResponse getApplicationById(String id);

    ApplicationsResponse createApplication(ApplicationsRequest request);

    ApplicationsResponse updateApplication(String id, ApplicationsRequest request);

    ApplicationsResponse deleteApplication(String id);

    List<ApplicationsResponse> getApplicationsByUserId(String userId);

    List<ApplicationsResponse> getApplicationsByStatus(ApplicationStatus status);

}
