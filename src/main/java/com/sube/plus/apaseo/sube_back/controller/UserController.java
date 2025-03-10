package com.sube.plus.apaseo.sube_back.controller;

import com.sube.plus.apaseo.sube_back.model.constant.SwaggerTags;
import com.sube.plus.apaseo.sube_back.model.constant.UserURIConstants;
import com.sube.plus.apaseo.sube_back.model.request.UserRequest;
import com.sube.plus.apaseo.sube_back.model.response.UserResponse;
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

import java.util.List;


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
    public String createUserApplicant(@RequestBody UserRequest userRequest) {
        return userService.createUserApplicant(userRequest);
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

    @Operation(summary = "Get User by ID", tags = SwaggerTags.USER)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User retrieved successfully."),
            @ApiResponse(responseCode = "404", description = "User not found.")
    })
    @GetMapping(value = UserURIConstants.USER_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserResponse getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }


    @Operation(summary = "Update email User", tags = SwaggerTags.PERSON)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User email updated successfully."),
            @ApiResponse(responseCode = "404", description = "User not found."),
            @ApiResponse(responseCode = "400", description = "Invalid data provided.")
    })
    @PutMapping(value = UserURIConstants.UPDATED_USER_VALIDATE_EMAIL, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserResponse updateEmailUser(@PathVariable String id, @PathVariable String email, @PathVariable String verificationCodeEmail) {
        return userService.updateEmailUser(id, email, verificationCodeEmail);
    }

    @Operation(summary = "Update phone User", tags = SwaggerTags.PERSON)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User phone updated successfully."),
            @ApiResponse(responseCode = "404", description = "User not found."),
            @ApiResponse(responseCode = "400", description = "Invalid data provided.")
    })
    @PutMapping(value = UserURIConstants.UPDATED_USER_VALIDATE_PHONE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserResponse updatePhoneUser(@PathVariable String id, @PathVariable String phone, @PathVariable String verificationCodePhone) {
        return userService.updatePhoneUser(id, phone, verificationCodePhone);
    }

    @Operation(summary = "Delete Applicant", tags = SwaggerTags.PERSON)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Applicant delete successfully."),
            @ApiResponse(responseCode = "404", description = "Applicant not found."),
            @ApiResponse(responseCode = "400", description = "Invalid data provided.")
    })
    @DeleteMapping(value = UserURIConstants.USER_APPLICANT_BY_ID, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteApplicant(@PathVariable String id) {
        userService.deleteApplicant(id);
    }

    @Operation(summary = "Delete Reviewer", tags = SwaggerTags.PERSON)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reviewer delete successfully."),
            @ApiResponse(responseCode = "404", description = "Reviewer not found."),
            @ApiResponse(responseCode = "400", description = "Invalid data provided.")
    })
    @DeleteMapping(value = UserURIConstants.USER_REVIEWER_BY_ID, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReviewer(@PathVariable String id) {
        userService.deleteReviewer(id);
    }

    @Operation(summary = "Send code reset password", tags = SwaggerTags.USER)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Send code reset password successful."),
            @ApiResponse(responseCode = "400", description = "Invalid request.")
    })
    @PostMapping(value = UserURIConstants.SEND_CODE_RESET_PASSWORD)
    @ResponseStatus(HttpStatus.OK)
    public void sendCodeResetPassword(@RequestParam("email") String email) {
        userService.sendCodeResetPassword(email);
    }

    @Operation(summary = "Validate code reset password", tags = SwaggerTags.USER)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Validate code reset password successful."),
            @ApiResponse(responseCode = "400", description = "Invalid request.")
    })
    @PostMapping(value = UserURIConstants.VALIDATE_CODE_RESET_PASSWORD)
    @ResponseStatus(HttpStatus.OK)
    public void validateCodeResetPassword(@RequestParam("id") String id, @RequestParam("code") String code) {
        userService.validateCodeResetPassword(id, code);
    }

    @Operation(summary = "Reset password", tags = SwaggerTags.USER)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Reset password successful."),
            @ApiResponse(responseCode = "400", description = "Invalid request.")
    })
    @PostMapping(value = UserURIConstants.RESET_PASSWORD)
    @ResponseStatus(HttpStatus.OK)
    public void resetPassword(@RequestParam("id") String id, @RequestParam("pwd") String pwd) {
        userService.resetPassword(id, pwd);
    }

    @Operation(summary = "Create User Reviewer", tags = SwaggerTags.USER)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Create User Reviewer successful."),
            @ApiResponse(responseCode = "400", description = "Invalid request.")
    })
    @PostMapping(value = UserURIConstants.USER_REVIEWER, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String createUserReviewer(@RequestBody UserRequest userRequest) {
        return userService.createUserReviewer(userRequest);
    }

    @Operation(summary = "Get all reviwer active", tags = SwaggerTags.USER)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User reviwer active successfully."),
            @ApiResponse(responseCode = "404", description = "User reviwer active not found.")
    })
    @GetMapping(value = UserURIConstants.USER_REVIEWER_ACTIVE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserResponse> getReviewerActive() {
        return userService.getReviewerActive();
    }

}
