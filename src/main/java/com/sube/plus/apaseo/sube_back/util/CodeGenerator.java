package com.sube.plus.apaseo.sube_back.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class CodeGenerator {

    public String generateVerificationCode() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(999999)); // Genera un código de 6 dígitos
    }
}
