package com.sube.plus.apaseo.sube_back.controller;

import com.sube.plus.apaseo.sube_back.model.constant.ApplicationsURIConstants;
import com.sube.plus.apaseo.sube_back.model.enums.ApplicationStatus;
import com.sube.plus.apaseo.sube_back.model.request.ApplicationsRequest;
import com.sube.plus.apaseo.sube_back.model.request.RejectionRequest;
import com.sube.plus.apaseo.sube_back.model.response.ApplicationsResponse;
import com.sube.plus.apaseo.sube_back.service.ApplicationsService;
import com.sube.plus.apaseo.sube_back.model.constant.SwaggerTags;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ApplicationsController {

    private final ApplicationsService applicationsService;

    @Operation(summary = "Create Application", tags = SwaggerTags.APPLICATION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Application created successfully."),
            @ApiResponse(responseCode = "400", description = "Invalid data provided.")
    })
    @PostMapping(
            value = ApplicationsURIConstants.APPLICATIONS,
            consumes = { MediaType.MULTIPART_FORM_DATA_VALUE },
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public ApplicationsResponse createApplication(
            @RequestPart("application") ApplicationsRequest request,
            @RequestPart("files") List<MultipartFile> files) {
        return applicationsService.createApplication(request, files);
    }

    @Operation(summary = "Update document in Application by documentId", tags = SwaggerTags.APPLICATION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Document updated successfully."),
            @ApiResponse(responseCode = "404", description = "Application or document not found.")
    })
    @PutMapping(
            value = ApplicationsURIConstants.DOCUMENTS_BY_APPLICATION_AND_DOCUMENT_ID,
            consumes = { MediaType.MULTIPART_FORM_DATA_VALUE },
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public ApplicationsResponse updateDocumentById(
            @PathVariable String applicationId,
            @PathVariable String documentId,
            @RequestPart("file") MultipartFile file
    ) {
        return applicationsService.updateDocumentById(applicationId, documentId, file);
    }

    @Operation(summary = "Reject a document in Application by documentId", tags = SwaggerTags.APPLICATION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Document rejected successfully."),
            @ApiResponse(responseCode = "404", description = "Application or document not found."),
            @ApiResponse(responseCode = "400", description = "Rejection reason is required.")
    })
    @PutMapping(
            value = ApplicationsURIConstants.DOCUMENTS_REJECT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public ApplicationsResponse rejectDocument(
            @PathVariable String applicationId,
            @PathVariable String documentId,
            @RequestBody RejectionRequest rejectionRequest
    ) {
        return applicationsService.rejectDocument(applicationId, documentId, rejectionRequest);
    }

    @Operation(summary = "Approve a document in Application by documentId", tags = SwaggerTags.APPLICATION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Document approved successfully."),
            @ApiResponse(responseCode = "404", description = "Application or document not found.")
    })
    @PutMapping(
            value =  ApplicationsURIConstants.DOCUMENTS_APPROVE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public ApplicationsResponse approveDocument(
            @PathVariable String applicationId,
            @PathVariable String documentId
    ) {
        return applicationsService.approveDocument(applicationId, documentId);
    }

    @Operation(summary = "Get Application by ID", tags = SwaggerTags.APPLICATION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Application retrieved successfully."),
            @ApiResponse(responseCode = "404", description = "Application not found.")
    })
    @GetMapping(value = ApplicationsURIConstants.APPLICATIONS_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ApplicationsResponse getApplicationById(@PathVariable String id) {
        return applicationsService.getApplicationById(id);
    }

    @Operation(summary = "Get All Applications", tags = SwaggerTags.APPLICATION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Applications retrieved successfully.")
    })
    @GetMapping(value = ApplicationsURIConstants.APPLICATIONS, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<ApplicationsResponse> getAllApplications() {
        return applicationsService.getAllApplications();
    }

    @Operation(summary = "Get Applications by UserId", tags = SwaggerTags.APPLICATION)
    @GetMapping(value = ApplicationsURIConstants.APPLICATIONS_BY_USER_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<ApplicationsResponse> getApplicationsByUserId(@PathVariable String userId) {
        return applicationsService.getApplicationsByUserId(userId);
    }

    @Operation(summary = "Get Applications by Status", tags = SwaggerTags.APPLICATION)
    @GetMapping(value = ApplicationsURIConstants.APPLICATIONS_BY_STATUS, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<ApplicationsResponse> getApplicationsByStatus(@PathVariable ApplicationStatus status) {
        return applicationsService.getApplicationsByStatus(status);
    }
}
