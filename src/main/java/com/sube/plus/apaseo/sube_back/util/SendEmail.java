package com.sube.plus.apaseo.sube_back.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class SendEmail {

    private final JavaMailSender emailSender;

    private final TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String emailFrom;


    public void sendVerificationEmail(String email, String verificationCode , String fullName) {

        MimeMessage mensaje = emailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mensaje, true);
            Context context = new Context();

            // Variables para enviar al HTML
            Map<String, Object> model = new HashMap<>();
            model.put("fullName", fullName);
            model.put("verificationCode", verificationCode);
            context.setVariables(model);

            //Se agrega el contexto a la plantilla
            String htmlText = templateEngine.process("send-verification-code-template", context);

            helper.setFrom(emailFrom);
            helper.setTo(email);
            helper.setSubject("Sube+ Instituto Municipal de la Juventud de Apaseo el Grande - Códigos de verificación");
            helper.setText(htmlText, true);

            emailSender.send(mensaje);

        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("Send successfully code for your email");
    }

    public void sendPasswordByEmail(String email, String password, String fullName) {

        MimeMessage mensaje = emailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mensaje, true);
            Context context = new Context();

            // Variables para enviar al HTML
            Map<String, Object> model = new HashMap<>();
            model.put("fullName", fullName);
            model.put("pwd", password);
            context.setVariables(model);

            //Se agrega el contexto a la plantilla
            String htmlText = templateEngine.process("send-pwd-template", context);

            helper.setFrom(emailFrom);
            helper.setTo(email);
            helper.setSubject("Sube+ Instituto Municipal de la Juventud de Apaseo el Grande - Acceso al portal");
            helper.setText(htmlText, true);

            emailSender.send(mensaje);

        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("Send successfully password for your email");
    }


    public void sendCodeResetPasswordByEmail(String email, String resetCodePassword, String fullName) {

        MimeMessage mensaje = emailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mensaje, true);
            Context context = new Context();

            // Variables para enviar al HTML
            Map<String, Object> model = new HashMap<>();
            model.put("fullName", fullName);
            model.put("code", resetCodePassword);
            context.setVariables(model);

            //Se agrega el contexto a la plantilla
            String htmlText = templateEngine.process("send-code-reset-pwd-template", context);

            helper.setFrom(emailFrom);
            helper.setTo(email);
            helper.setSubject("Sube+ Instituto Municipal de la Juventud de Apaseo el Grande - Acceso al portal");
            helper.setText(htmlText, true);

            emailSender.send(mensaje);

        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("Send code successfully for your email");
    }

    public void sendChangePasswordSuccessfulByEmail(String email, String fullName) {

        MimeMessage mensaje = emailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mensaje, true);
            Context context = new Context();

            // Variables para enviar al HTML
            Map<String, Object> model = new HashMap<>();
            model.put("fullName", fullName);
            context.setVariables(model);

            //Se agrega el contexto a la plantilla
            String htmlText = templateEngine.process("send-reset-pwd-successful-template", context);

            helper.setFrom(emailFrom);
            helper.setTo(email);
            helper.setSubject("Sube+ Instituto Municipal de la Juventud de Apaseo el Grande - Acceso al portal");
            helper.setText(htmlText, true);

            emailSender.send(mensaje);

        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("Send code successfully for your email");
    }
}
