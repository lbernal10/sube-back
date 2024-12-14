package com.sube.plus.apaseo.sube_back.service.impl;

import com.sube.plus.apaseo.sube_back.converter.UserMapper;
import com.sube.plus.apaseo.sube_back.model.User;
import com.sube.plus.apaseo.sube_back.model.constant.EmailConstants;
import com.sube.plus.apaseo.sube_back.model.enums.UserStatus;
import com.sube.plus.apaseo.sube_back.model.enums.UserType;
import com.sube.plus.apaseo.sube_back.model.request.UserRequest;
import com.sube.plus.apaseo.sube_back.model.response.PersonResponse;
import com.sube.plus.apaseo.sube_back.repository.UserRepository;
import com.sube.plus.apaseo.sube_back.service.PersonService;
import com.sube.plus.apaseo.sube_back.service.UserService;
import com.sube.plus.apaseo.sube_back.util.CodeGenerator;
import com.sube.plus.apaseo.sube_back.util.PasswordGenerator;
import com.sube.plus.apaseo.sube_back.util.SendEmail;
import com.sube.plus.apaseo.sube_back.util.SendPhone;
import com.sube.plus.apaseo.sube_back.util.exceptions.BadRequestException;
import com.sube.plus.apaseo.sube_back.util.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final SendEmail sendEmail;
    private final SendPhone sendPhone;
    private final PasswordEncoder passwordEncoder;
    private final PasswordGenerator passwordGenerator;
    private final CodeGenerator codeGenerator;
    private final UserMapper userMapper;
    private final PersonService personService;

    @Override
    public void createUserApplicant(UserRequest userRequest) {
        validateEmail(userRequest.getEmail());
        validateEmailStructure(userRequest.getEmail());

        PersonResponse personResponse = personService.getPersonById(userRequest.getPersonId());

        User user = userMapper.toUser(userRequest);

        // Set status PREACTIVE
        user.setStatus(UserStatus.PREACTIVE);

        // Set user type APPLICANT
        user.setType(UserType.APPLICANT);

        // Send code email
        user.setVerifyEmail(false);
        user.setVerificationCodeEmail(null);
        user.setVerificationCodeEmailSentAt(null);

        // Send code phone whatsapp
        user.setVerifyPhone(false);
        user.setVerificationCodePhone(null);
        user.setVerificationCodePhoneSentAt(null);

        user.setCreatedAt(LocalDate.now());
        user.setUpdatedAt(LocalDate.now());
        user.setLastAccess(null);

        User userSave = userRepository.save(user);

        //sendVerificationCodeEmail(userSave.getId());
        //sendVerificationCodePhone(userSave.getId(), personResponse.getPhone());
        //setAndSendRandomPassword(userSave.getId());
    }

    public void sendVerificationCodeEmail(String id, String email) {
        final User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found with id: " + id));

        final String verificationCodeEmail = codeGenerator.generateVerificationCode();

        user.setVerifyEmail(false);
        user.setVerificationCodeEmail(verificationCodeEmail);
        user.setVerificationCodeEmailSentAt(LocalDateTime.now());
        user.setEmail(email);

        User userSave = userRepository.save(user);

        sendEmail.sendVerificationEmail(userSave.getEmail(), verificationCodeEmail);
    }

    public void sendVerificationCodePhone(String id, String phone) {
        final User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found with id: " + id));

        final String verificationCodePhone = codeGenerator.generateVerificationCode();

        user.setVerifyPhone(false);
        user.setVerificationCodePhone(verificationCodePhone);
        user.setVerificationCodePhoneSentAt(LocalDateTime.now());
        user.setPhone(phone);

        User userSave = userRepository.save(user);

        sendPhone.sendVerificationPhone(userSave.getPhone(), verificationCodePhone);
    }

    public void verifyUserEmail(String id, String verificationCodeEmail) {
        final User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found with id: " + id));

        if (user.getVerificationCodeEmail().equals(verificationCodeEmail) &&
                user.getVerificationCodeEmailSentAt().plusMinutes(15).isAfter(LocalDateTime.now())) {

            user.setVerifyEmail(true);

            User userSave = userRepository.save(user);

            verifyEmailAndPhone(userSave.getId());
        } else {
            throw new BadRequestException("Invalid or expired verification code");
        }
    }

    public void verifyUserPhone(String id, String verificationCodePhone) {
        final User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found with id: " + id));

        if (user.getVerificationCodePhone().equals(verificationCodePhone) &&
                user.getVerificationCodePhoneSentAt().plusMinutes(15).isAfter(LocalDateTime.now())) {

            user.setVerifyPhone(true);

            User userSave = userRepository.save(user);

            verifyEmailAndPhone(userSave.getId());
        } else {
            throw new BadRequestException("Invalid or expired verification code");
        }
    }

    private void verifyEmailAndPhone(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found with id: " + id));

        if (user.getVerifyEmail() && user.getVerifyPhone()) {
            user.setStatus(UserStatus.ACTIVE);
            userRepository.save(user);
        }
    }

    private Boolean validateEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new BadRequestException("El email ya existe: " + email);
        }
        return true;
    }

    private Boolean validateEmailStructure(String email) {
        if (email == null || !EmailConstants.EMAIL_PATTERN.matcher(email).matches()) {
            throw new BadRequestException("El email tiene una estructura inválida: " + email);
        }
        return true;
    }

    public void setAndSendRandomPassword(String id, String email) {
        final User user = userRepository.findByIdAndEmail(id, email).orElseThrow(() -> new NotFoundException("User not found with id: " + id));

        final String passwordRandom = passwordGenerator.generateRandomPassword();

        log.info("Password: {}", passwordRandom);

        user.setPassword(passwordEncoder.encode(passwordRandom));

        userRepository.save(user);

        sendEmail.sendPasswordByEmail(user.getEmail(), passwordRandom);
    }

}
