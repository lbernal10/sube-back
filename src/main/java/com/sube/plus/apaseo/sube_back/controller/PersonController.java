package com.sube.plus.apaseo.sube_back.controller;

import com.sube.plus.apaseo.sube_back.model.constant.PersonURIConstants;
import com.sube.plus.apaseo.sube_back.model.constant.SwaggerTags;
import com.sube.plus.apaseo.sube_back.model.request.PersonRequest;
import com.sube.plus.apaseo.sube_back.model.response.PersonResponse;
import com.sube.plus.apaseo.sube_back.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController {

    private final PersonService personService;

    @Operation(summary = "Create Person", tags = SwaggerTags.PERSON)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Create Person successful."),
            @ApiResponse(responseCode = "404", description = "Person Not found.")
    })
    @PostMapping(value = PersonURIConstants.PERSON, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PersonResponse createPerson(@RequestBody PersonRequest personRequest) {
        return personService.createPerson(personRequest);
    }

    @Operation(summary = "Get Person by ID", tags = SwaggerTags.PERSON)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Person retrieved successfully."),
            @ApiResponse(responseCode = "404", description = "Person not found.")
    })
    @GetMapping(value = PersonURIConstants.PERSON_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonResponse getPersonById(@PathVariable String id) {
        return personService.getPersonById(id);
    }

    @Operation(summary = "Update Person", tags = SwaggerTags.PERSON)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Person updated successfully."),
            @ApiResponse(responseCode = "404", description = "Person not found."),
            @ApiResponse(responseCode = "400", description = "Invalid data provided.")
    })
    @PutMapping(value = PersonURIConstants.PERSON_BY_ID, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonResponse updatePerson(@PathVariable String id, @RequestBody PersonRequest personRequest) {
        return personService.updatePerson(id, personRequest);
    }
}
