package com.sube.plus.apaseo.sube_back.controller;

import com.sube.plus.apaseo.sube_back.model.constant.TemplateURIConstants;
import com.sube.plus.apaseo.sube_back.model.constant.SwaggerTags;
import com.sube.plus.apaseo.sube_back.model.response.TemplateResponse;
import com.sube.plus.apaseo.sube_back.service.TemplateService;
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

import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TemplateController {

    private final TemplateService templateService;

    @Operation(summary = "Create Template", tags = SwaggerTags.TEMPLATE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Template created successfully."),
            @ApiResponse(responseCode = "400", description = "Invalid data provided.")
    })
    @PostMapping(value = TemplateURIConstants.TEMPLATE, produces = MediaType.APPLICATION_JSON_VALUE,  consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public TemplateResponse createTemplate(@RequestParam("name") String name, @RequestParam("file") MultipartFile file) throws IOException {
        return templateService.createTemplate(name, file);
    }

    @Operation(summary = "Get Template by ID", tags = SwaggerTags.TEMPLATE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Template retrieved successfully."),
            @ApiResponse(responseCode = "404", description = "Template not found.")
    })
    @GetMapping(value = TemplateURIConstants.TEMPLATE_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public TemplateResponse getTemplateById(@PathVariable String id) {
        return templateService.getTemplateById(id);
    }

    @Operation(summary = "Get All Templates", tags = SwaggerTags.TEMPLATE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Templates retrieved successfully.")
    })
    @GetMapping(value = TemplateURIConstants.TEMPLATES, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TemplateResponse> getAllTemplates() {
        return templateService.getAllTemplates();
    }

    @Operation(summary = "Update Template", tags = SwaggerTags.TEMPLATE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Template updated successfully."),
            @ApiResponse(responseCode = "404", description = "Template not found."),
            @ApiResponse(responseCode = "400", description = "Invalid data provided.")
    })
    @PutMapping(value = TemplateURIConstants.TEMPLATE_UPDATE, produces = MediaType.APPLICATION_JSON_VALUE,  consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public TemplateResponse updateTemplate(@RequestParam("id") String id, @RequestParam("name") String name, @RequestParam("file") MultipartFile file) throws IOException {
        return templateService.updateTemplate(id, name, file);
    }

    @Operation(summary = "Delete Template", tags = SwaggerTags.TEMPLATE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Template deleted successfully."),
            @ApiResponse(responseCode = "404", description = "Template not found.")
    })
    @DeleteMapping(value = TemplateURIConstants.TEMPLATE_BY_ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public TemplateResponse deleteTemplate(@RequestParam("id") String id) {
        return templateService.deleteTemplate(id);
    }

    @Operation(summary = "Download File", tags = SwaggerTags.TEMPLATE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "File downloaded successfully."),
            @ApiResponse(responseCode = "404", description = "File not found.")
    })
    @GetMapping(value = TemplateURIConstants.TEMPLATE_DOWNLOAD)
    @ResponseStatus(HttpStatus.OK)
    public byte[] downloadFile(@RequestParam("fileName") String fileName) {
        log.info("Received file name: {}", fileName);  // Agregar un log para verificar el valor de fileName
        return templateService.downloadFile(fileName);
    }
}