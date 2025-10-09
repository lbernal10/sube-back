package com.sube.plus.apaseo.sube_back.service;

import com.sube.plus.apaseo.sube_back.model.enums.ApplicationStatus;
import com.sube.plus.apaseo.sube_back.model.request.*;
import com.sube.plus.apaseo.sube_back.model.response.ApplicationsResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ApplicationsService {

    List<ApplicationsResponse> getAllApplications();

    ApplicationsResponse getApplicationById(String id);

    ApplicationsResponse createApplication(ApplicationsRequest request, List<MultipartFile> files);

    List<ApplicationsResponse> getApplicationsByUserId(String userId);

    List<ApplicationsResponse> getApplicationsByStatus(ApplicationStatus status);

    ApplicationsResponse updateDocumentById(String applicationId, String documentId, MultipartFile file);

    ApplicationsResponse rejectDocument(String applicationId, String documentId, RejectionRequest rejectionRequest);

    ApplicationsResponse approveDocument(String applicationId, String documentId);

    ApplicationsResponse updateApplicationInfo(String applicationId, ApplicationsUpdateRequest request);

    ApplicationsResponse updateApplicationStatus(String applicationId, ApplicationStatusUpdateRequest request);

    ApplicationsResponse uploadEvidenceDocument(String applicationId, String name, String description, MultipartFile file);

}
