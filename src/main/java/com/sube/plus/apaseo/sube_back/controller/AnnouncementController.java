package com.sube.plus.apaseo.sube_back.controller;

import com.sube.plus.apaseo.sube_back.model.constant.AnnouncementURIConstants;
import com.sube.plus.apaseo.sube_back.model.constant.SwaggerTags;
import com.sube.plus.apaseo.sube_back.model.response.AnnouncementResponse;
import com.sube.plus.apaseo.sube_back.service.AnnouncementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AnnouncementController {

    private final AnnouncementService announcementService;

    @Operation(summary = "Create Announcement", tags = SwaggerTags.ANNOUNCEMENT)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Announcement created successfully."),
            @ApiResponse(responseCode = "400", description = "Invalid data provided.")
    })
    @PostMapping(value = AnnouncementURIConstants.ANNOUNCEMENT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public AnnouncementResponse createAnnouncement(@RequestParam("idProgram") String idProgram,
                                                   @RequestParam("name") String name,
                                                   @RequestParam("description") String description,
                                                   @RequestParam("beneficiaries") Integer beneficiaries,
                                                   @RequestParam("dateStart") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateStart,
                                                   @RequestParam("dateFinish") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFinish,
                                                   @RequestParam("applicationDocument") MultipartFile applicationDocument,
                                                   @RequestParam("specificationDocument") MultipartFile specificationDocument) throws IOException {
        return announcementService.createAnnouncement(idProgram, name, description, beneficiaries, dateStart, dateFinish, applicationDocument, specificationDocument);
    }

    @Operation(summary = "Update Announcement", tags = SwaggerTags.ANNOUNCEMENT)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Announcement updated successfully."),
            @ApiResponse(responseCode = "400", description = "Invalid data provided."),
            @ApiResponse(responseCode = "404", description = "Announcement not found.")
    })
    @PutMapping(value = AnnouncementURIConstants.ANNOUNCEMENT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public AnnouncementResponse updateAnnouncement(@RequestParam("id") String id,
                                                   @RequestParam("idProgram") String idProgram,
                                                   @RequestParam("name") String name,
                                                   @RequestParam("description") String description,
                                                   @RequestParam("beneficiaries") Integer beneficiaries,
                                                   @RequestParam("dateStart") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateStart,
                                                   @RequestParam("dateFinish") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFinish,
                                                   @RequestParam(value = "applicationDocument", required = false) MultipartFile applicationDocument,
                                                   @RequestParam(value = "specificationDocument", required = false) MultipartFile specificationDocument) throws IOException {
        return announcementService.updateAnnouncement(id, idProgram, name, description, beneficiaries, dateStart, dateFinish, applicationDocument, specificationDocument);
    }

    @Operation(summary = "Get Announcement by ID", tags = SwaggerTags.ANNOUNCEMENT)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Announcement retrieved successfully."),
            @ApiResponse(responseCode = "404", description = "Announcement not found.")
    })
    @GetMapping(value = AnnouncementURIConstants.ANNOUNCEMENT_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public AnnouncementResponse getAnnouncementById(@PathVariable String id) {
        return announcementService.getAnnouncementById(id);
    }

    @Operation(summary = "Get All Announcements", tags = SwaggerTags.ANNOUNCEMENT)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Announcements retrieved successfully.")
    })
    @GetMapping(value = AnnouncementURIConstants.ANNOUNCEMENTS, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<AnnouncementResponse> getAllAnnouncements() {
        return announcementService.getAllAnnouncements();
    }

    @Operation(summary = "Delete Announcement", tags = SwaggerTags.ANNOUNCEMENT)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Announcement deleted successfully."),
            @ApiResponse(responseCode = "404", description = "Announcement not found.")
    })
    @DeleteMapping(value = AnnouncementURIConstants.ANNOUNCEMENT)
    @ResponseStatus(HttpStatus.OK)
    public AnnouncementResponse deleteAnnouncement(@RequestParam("id") String id) {
        return announcementService.deleteAnnouncement(id);
    }

    @Operation(summary = "Get Announcement by idProgram Active", tags = SwaggerTags.ANNOUNCEMENT)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Announcement retrieved successfully."),
            @ApiResponse(responseCode = "404", description = "Announcement not found.")
    })
    @GetMapping(value = AnnouncementURIConstants.ANNOUNCEMENT_BY_ID_PROGRAM, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<AnnouncementResponse> getAnnouncementByIdProgram(@PathVariable String idProgram) {
        return announcementService.getAnnouncementByIdProgram(idProgram);
    }
}