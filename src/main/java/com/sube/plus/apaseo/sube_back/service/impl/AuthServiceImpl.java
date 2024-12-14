package com.sube.plus.apaseo.sube_back.service.impl;

import com.sube.plus.apaseo.sube_back.model.enums.UserStatus;
import com.sube.plus.apaseo.sube_back.model.request.LoginRequest;
import com.sube.plus.apaseo.sube_back.repository.UserRepository;
import com.sube.plus.apaseo.sube_back.service.AuthService;
import com.sube.plus.apaseo.sube_back.util.exceptions.InvalidCredentialsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void login(LoginRequest loginRequest) {
        userRepository.findByEmail(loginRequest.getEmail())
                .filter(user -> user.getStatus() == UserStatus.ACTIVE)
                .filter(user -> passwordEncoder.matches(loginRequest.getPassword(), user.getPassword()))
                .orElseThrow(() -> new InvalidCredentialsException("Invalid credentials or inactive user."));
    }
}
