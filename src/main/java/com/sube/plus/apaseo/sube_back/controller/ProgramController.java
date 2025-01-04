package com.sube.plus.apaseo.sube_back.controller;

import com.sube.plus.apaseo.sube_back.model.Program;
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
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Program> getAllPrograms() {
        return programService.getAllPrograms();
    }

    @Operation(summary = "Get Program by ID", tags = SwaggerTags.PROGRAM)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Program retrieved successfully."),
            @ApiResponse(responseCode = "404", description = "Program not found.")
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Program getProgramById(@PathVariable String id) {
        return programService.getProgramById(id);
    }

    @Operation(summary = "Update a Program", tags = SwaggerTags.PROGRAM)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Program updated successfully."),
            @ApiResponse(responseCode = "404", description = "Program not found.")
    })
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Program updateProgram(@PathVariable String id, @RequestBody Program program) {
        return programService.updateProgram(id, program);
    }

    @Operation(summary = "Delete a Program", tags = SwaggerTags.PROGRAM)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Program deleted successfully."),
            @ApiResponse(responseCode = "404", description = "Program not found.")
    })
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProgram(@PathVariable String id) {
        programService.deleteProgram(id);
    }
}