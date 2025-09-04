package com.sube.plus.apaseo.sube_back.controller;

import com.sube.plus.apaseo.sube_back.model.constant.AnnouncementURIConstants;
import com.sube.plus.apaseo.sube_back.model.constant.ApplicationsURIConstants;
import com.sube.plus.apaseo.sube_back.model.enums.ApplicationStatus;
import com.sube.plus.apaseo.sube_back.model.request.ApplicationsRequest;
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
    @PostMapping(value = ApplicationsURIConstants.APPLICATIONS, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ApplicationsResponse createApplication(@RequestBody ApplicationsRequest request) {
        return applicationsService.createApplication(request);
    }

    @Operation(summary = "Update Application", tags = SwaggerTags.APPLICATION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Application updated successfully."),
            @ApiResponse(responseCode = "404", description = "Application not found.")
    })
    @PutMapping(value = ApplicationsURIConstants.APPLICATIONS_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ApplicationsResponse updateApplication(@PathVariable String id, @RequestBody ApplicationsRequest request) {
        return applicationsService.updateApplication(id, request);
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

    @Operation(summary = "Delete Application", tags = SwaggerTags.APPLICATION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Application deleted successfully."),
            @ApiResponse(responseCode = "404", description = "Application not found.")
    })
    @DeleteMapping(value = ApplicationsURIConstants.APPLICATIONS_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ApplicationsResponse deleteApplication(@PathVariable String id) {
        return applicationsService.deleteApplication(id);
    }

    @Operation(summary = "Get Applications by UserId", tags = SwaggerTags.APPLICATION)
    @GetMapping(value = "/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<ApplicationsResponse> getApplicationsByUserId(@PathVariable String userId) {
        return applicationsService.getApplicationsByUserId(userId);
    }

    @Operation(summary = "Get Applications by Status", tags = SwaggerTags.APPLICATION)
    @GetMapping(value = "/status/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<ApplicationsResponse> getApplicationsByStatus(@PathVariable ApplicationStatus status) {
        return applicationsService.getApplicationsByStatus(status);
    }
}
