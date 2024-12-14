package com.sube.plus.apaseo.sube_back.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable() // Deshabilitar CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/**").permitAll() // Permitir acceso a todas las rutas
                        .anyRequest().permitAll() // Permitir todas las solicitudes sin autenticación
                );
        return http.build();
    }
}
