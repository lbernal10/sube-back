package com.sube.plus.apaseo.sube_back.controller;

import com.sube.plus.apaseo.sube_back.model.constant.ProgramURIConstants;
import com.sube.plus.apaseo.sube_back.model.constant.SwaggerTags;
import com.sube.plus.apaseo.sube_back.model.request.ProgramRequest;
import com.sube.plus.apaseo.sube_back.model.response.ProgramResponse;
import com.sube.plus.apaseo.sube_back.service.ProgramService;
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
public class ProgramController {

    private final ProgramService programService;

    @Operation(summary = "Create a new Program", tags = SwaggerTags.PROGRAM)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Program created successfully."),
            @ApiResponse(responseCode = "400", description = "Invalid request.")
    })
    @PostMapping(value = ProgramURIConstants.PROGRAMS, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ProgramResponse createProgram(@RequestBody ProgramRequest programRequest) {
        return programService.createProgram(programRequest);
    }

    @Operation(summary = "Get all Programs", tags = SwaggerTags.PROGRAM)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Programs retrieved successfully."),
            @ApiResponse(responseCode = "404", description = "Programs not found.")
    })
    @GetMapping(value = ProgramURIConstants.PROGRAMS, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<ProgramResponse> getAllPrograms() {
        return programService.getAllPrograms();
    }

    @Operation(summary = "Get Program by ID", tags = SwaggerTags.PROGRAM)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Program retrieved successfully."),
            @ApiResponse(responseCode = "404", description = "Program not found.")
    })
    @GetMapping(value = ProgramURIConstants.PROGRAM_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ProgramResponse getProgramById(@PathVariable String id) {
        return programService.getProgramById(id);
    }

    @Operation(summary = "Update a Program", tags = SwaggerTags.PROGRAM)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Program updated successfully."),
            @ApiResponse(responseCode = "404", description = "Program not found.")
    })
    @PutMapping(value = ProgramURIConstants.PROGRAM_BY_ID, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ProgramResponse updateProgram(@PathVariable String id, @RequestBody ProgramRequest programRequest) {
        return programService.updateProgram(id, programRequest);
    }

    @Operation(summary = "Delete a Program", tags = SwaggerTags.PROGRAM)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Program deleted successfully."),
            @ApiResponse(responseCode = "404", description = "Program not found.")
    })
    @DeleteMapping(value = ProgramURIConstants.PROGRAM_BY_ID, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ProgramResponse deleteProgram(@PathVariable String id) {
        return programService.deleteProgram(id);
    }
}