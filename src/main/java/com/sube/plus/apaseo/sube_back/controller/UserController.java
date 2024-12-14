package com.sube.plus.apaseo.sube_back.controller;

import com.sube.plus.apaseo.sube_back.model.constant.SwaggerTags;
import com.sube.plus.apaseo.sube_back.model.constant.UserURIConstants;
import com.sube.plus.apaseo.sube_back.model.request.UserRequest;
import com.sube.plus.apaseo.sube_back.service.UserService;
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
public class UserController {

    private final UserService userService;

    @Operation(summary = "Create User applicant", tags = SwaggerTags.USER)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Create User applicant successful."),
            @ApiResponse(responseCode = "400", description = "Invalid request.")
    })
    @PostMapping(value = UserURIConstants.USER_APPLICANT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createUserApplicant(@RequestBody UserRequest userRequest) {
        userService.createUserApplicant(userRequest);
    }

    @Operation(summary = "Validate code email", tags = SwaggerTags.USER)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Validate code email successful."),
            @ApiResponse(responseCode = "400", description = "Invalid request.")
    })
    @PostMapping(value = UserURIConstants.USER_VALIDATE_EMAIL)
    @ResponseStatus(HttpStatus.OK)
    public void verifyUserEmail(@PathVariable String id, @PathVariable String verificationCodeEmail) {
        userService.verifyUserEmail(id, verificationCodeEmail);
    }

    @Operation(summary = "Validate code phone", tags = SwaggerTags.USER)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Validate code phone successful."),
            @ApiResponse(responseCode = "400", description = "Invalid request.")
    })
    @PostMapping(value = UserURIConstants.USER_VALIDATE_PHONE)
    @ResponseStatus(HttpStatus.OK)
    public void verifyUserPhone(@PathVariable String id, @PathVariable String verificationCodePhone) {
        userService.verifyUserPhone(id, verificationCodePhone);
    }

    @Operation(summary = "Send code email", tags = SwaggerTags.USER)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Send code email successful."),
            @ApiResponse(responseCode = "400", description = "Invalid request.")
    })
    @PostMapping(value = UserURIConstants.SEND_USER_VALIDATE_EMAIL)
    @ResponseStatus(HttpStatus.OK)
    public void sendVerificationCodeEmail(@PathVariable String id, @PathVariable String email) {
        userService.sendVerificationCodeEmail(id, email);
    }

    @Operation(summary = "Send code phone", tags = SwaggerTags.USER)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Send code phone successful."),
            @ApiResponse(responseCode = "400", description = "Invalid request.")
    })
    @PostMapping(value = UserURIConstants.SEND_USER_VALIDATE_PHONE)
    @ResponseStatus(HttpStatus.OK)
    public void sendVerificationCodePhone(@PathVariable String id, @PathVariable String phone) {
        userService.sendVerificationCodePhone(id, phone);
    }

    @Operation(summary = "Send password encode", tags = SwaggerTags.USER)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Send password encode."),
            @ApiResponse(responseCode = "400", description = "Invalid request.")
    })
    @PostMapping(value = UserURIConstants.SEND_USER_PASSWORD)
    @ResponseStatus(HttpStatus.OK)
    public void setAndSendRandomPassword(@PathVariable String id, @PathVariable String email) {
        userService.setAndSendRandomPassword(id, email);
    }
}
