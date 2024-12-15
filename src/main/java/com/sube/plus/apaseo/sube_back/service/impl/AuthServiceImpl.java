package com.sube.plus.apaseo.sube_back.service.impl;

import com.sube.plus.apaseo.sube_back.model.enums.UserStatus;
import com.sube.plus.apaseo.sube_back.model.request.LoginRequest;
import com.sube.plus.apaseo.sube_back.repository.UserRepository;
import com.sube.plus.apaseo.sube_back.service.AuthService;
import com.sube.plus.apaseo.sube_back.util.exceptions.InvalidCredentialsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthServiceImpl implements AuthService, UserDetailsService {

    @Value("${app.secret}") // Correcto si la propiedad es app.secret
    private String appSecret;


    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;

    @Override
    public void login(LoginRequest loginRequest) {
        userRepository.findByEmail(loginRequest.getEmail())
                .filter(user -> user.getStatus() == UserStatus.ACTIVE)
//                .filter(user -> passwordEncoder.matches(loginRequest.getPassword(), user.getPassword()))
                .orElseThrow(() -> new InvalidCredentialsException("Invalid credentials or inactive user."));

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info(email);

        log.info("Valor de app.secret: " + appSecret);

        com.sube.plus.apaseo.sube_back.model.User usuario = userRepository.findByEmail(email).get();

        if(usuario == null) {
            log.error("Error en el login");
            throw new UsernameNotFoundException("Error en el login");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(usuario.getType().name()));

        return new User(usuario.getEmail(), usuario.getPassword(), usuario.getStatus().isActive(), true, true, true, authorities);
    }
}
