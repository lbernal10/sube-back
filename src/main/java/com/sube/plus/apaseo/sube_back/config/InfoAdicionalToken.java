package com.sube.plus.apaseo.sube_back.config;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sube.plus.apaseo.sube_back.converter.UserMapper;
import com.sube.plus.apaseo.sube_back.model.Person;
import com.sube.plus.apaseo.sube_back.model.User;
import com.sube.plus.apaseo.sube_back.model.response.UserResponse;
import com.sube.plus.apaseo.sube_back.repository.PersonRepository;
import com.sube.plus.apaseo.sube_back.repository.UserRepository;
import com.sube.plus.apaseo.sube_back.util.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InfoAdicionalToken implements TokenEnhancer{

    private final PersonRepository personRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        log.info("Name: {}", authentication.getName());

        Map<String, Object> info = new HashMap<>();

        try {
            final User user = userRepository.findByEmail(authentication.getName())
                    .orElseThrow(() -> new NotFoundException("User not found with email: " + authentication.getName()));

            final Person person = personRepository.findById(user.getPersonId())
                    .orElseThrow(() -> new NotFoundException("Person not found with ID: " + user.getPersonId()));

            UserResponse userResponse = userMapper.toUserResponse(user);

            info.put("user", userResponse);
            //info.put("person", person);

        } catch (NotFoundException e) {
            log.error("Error enhancing token: {}", e.getMessage());
            // Opcional: Agregar datos mínimos para continuar
            info.put("error", e.getMessage());
        }

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
        return accessToken;
    }
}