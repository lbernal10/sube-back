package com.sube.plus.apaseo.sube_back.controller;

import com.sube.plus.apaseo.sube_back.model.constant.AuthorizationURIConstants;
import com.sube.plus.apaseo.sube_back.model.constant.SwaggerTags;
import com.sube.plus.apaseo.sube_back.model.request.LoginRequest;
import com.sube.plus.apaseo.sube_back.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Validate Login", tags = SwaggerTags.AUTH)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Login successful."),
            @ApiResponse(responseCode = "401", description = "Invalid credentials or inactive user."),
    })
    @PostMapping(AuthorizationURIConstants.AUTH)
    @ResponseStatus(HttpStatus.CREATED)
    public void login(@RequestBody LoginRequest loginRequest) {
        authService.login(loginRequest);
    }

}
