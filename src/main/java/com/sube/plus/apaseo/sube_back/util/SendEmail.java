package com.sube.plus.apaseo.sube_back.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class SendEmail {

    //private final JavaMailSender mailSender;

    public void sendVerificationEmail(String email, String verificationCode) {
       /*
       SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Verification Code for Your Account");
        message.setText("Your verification code is: " + verificationCode);
        mailSender.send(message);
        */
        log.info("Send successfully code for your email");
    }

    public void sendPasswordByEmail(String email, String password) {
       /*
       SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Verification password for Your Account");
        message.setText("Your password code is: " + password);
        mailSender.send(message);
        */
        log.info("Send successfully password for your email");
    }

}
