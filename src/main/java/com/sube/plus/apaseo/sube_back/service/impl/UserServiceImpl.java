package com.sube.plus.apaseo.sube_back.service.impl;

import com.sube.plus.apaseo.sube_back.converter.UserMapper;
import com.sube.plus.apaseo.sube_back.model.User;
import com.sube.plus.apaseo.sube_back.model.constant.EmailConstants;
import com.sube.plus.apaseo.sube_back.model.enums.UserStatus;
import com.sube.plus.apaseo.sube_back.model.enums.UserType;
import com.sube.plus.apaseo.sube_back.model.request.UserRequest;
import com.sube.plus.apaseo.sube_back.model.response.PersonResponse;
import com.sube.plus.apaseo.sube_back.model.response.ReviewerResponse;
import com.sube.plus.apaseo.sube_back.model.response.UserResponse;
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
import java.util.List;
import java.util.stream.Collectors;

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
    public String createUserApplicant(UserRequest userRequest) {
        validateEmail(userRequest.getEmail());
        validateEmailStructure(userRequest.getEmail());

        personService.getPersonById(userRequest.getPersonId());

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

        return userSave.getId();
    }

    public void sendVerificationCodeEmail(String id, String email) {
        final User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found with id: " + id));
        PersonResponse person = personService.getPersonById(user.getPersonId());

/*        if (user.getVerifyEmail()) {
            throw new BadRequestException("Invalid the email has already been verified");
        }*/

        final String verificationCodeEmail = codeGenerator.generateVerificationCode();

        user.setVerifyEmail(false);
        user.setVerificationCodeEmail(verificationCodeEmail);
        user.setVerificationCodeEmailSentAt(LocalDateTime.now());
        user.setEmail(email);

        User userSave = userRepository.save(user);

        sendEmail.sendVerificationEmail(userSave.getEmail(), verificationCodeEmail, person.getFullName());
    }

    public void sendVerificationCodePhone(String id, String phone) {
        final User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found with id: " + id));

/*        if (user.getVerifyPhone()) {
            throw new BadRequestException("Invalid the phone has already been verified");
        }*/

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
        PersonResponse person = personService.getPersonById(user.getPersonId());

        final String passwordRandom = passwordGenerator.generateRandomPassword();

        log.info("Password: {}", passwordRandom);

        user.setPassword(passwordEncoder.encode(passwordRandom));

        userRepository.save(user);

        sendEmail.sendPasswordByEmail(user.getEmail(), passwordRandom, person.getFullName());
    }

    @Override
    public UserResponse getUserById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));

        return userMapper.toUserResponse(user);
    }

    @Override
    public UserResponse updateEmailUser(String id, String email, String verificationCodeEmail) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));

        verifyUserEmail(id, verificationCodeEmail);

        // Actualizar los campos
        existingUser.setEmail(email);
        existingUser.setVerifyEmail(true);
        existingUser.setUpdatedAt(LocalDate.now());

        User updatedUser = userRepository.save(existingUser);

        return userMapper.toUserResponse(updatedUser);
    }

    @Override
    public UserResponse updatePhoneUser(String id, String phone, String verificationCodePhone) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));

        verifyUserPhone(id, verificationCodePhone);

        // Actualizar los campos
        existingUser.setPhone(phone);
        existingUser.setVerifyPhone(true);
        existingUser.setUpdatedAt(LocalDate.now());

        User updatedUser = userRepository.save(existingUser);

        return userMapper.toUserResponse(updatedUser);
    }

    @Override
    public void deleteApplicant(String id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));


        // Verificar si el tipo de usuario es APPLICANT
        if (!UserType.APPLICANT.equals(existingUser.getType())) {
            throw new BadRequestException("Operation not allowed for user type: " + existingUser.getType());
        }

        // Actualizar los campos
        existingUser.setStatus(UserStatus.INACTIVE);
        existingUser.setUpdatedAt(LocalDate.now());

        userRepository.save(existingUser);
    }

    @Override
    public void deleteReviewer(String id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));


        // Verificar si el tipo de usuario es REVIEWER
        if (!UserType.REVIEWER.equals(existingUser.getType())) {
            throw new BadRequestException("Operation not allowed for user type: " + existingUser.getType());
        }

        // Actualizar los campos
        existingUser.setStatus(UserStatus.INACTIVE);
        existingUser.setUpdatedAt(LocalDate.now());

        userRepository.save(existingUser);
    }

    @Override
    public void sendCodeResetPassword(String email) {
        final User user = userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User not found with email: " + email));
        PersonResponse person = personService.getPersonById(user.getPersonId());

        final String resetCodePassword = codeGenerator.generateVerificationCode();

        user.setResetCodePassword(resetCodePassword);
        user.setResetCodePasswordSentAt(LocalDateTime.now());

        userRepository.save(user);

        sendEmail.sendCodeResetPasswordByEmail(user.getEmail(), resetCodePassword, person.getFullName());
        sendPhone.sendCodeResetPasswordByEmail(user.getPhone(), resetCodePassword);
    }

    @Override
    public void validateCodeResetPassword(String id, String code) {
        final User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found with id: " + id));

        if (user.getResetCodePassword().equals(code) &&
                user.getResetCodePasswordSentAt().plusMinutes(15).isAfter(LocalDateTime.now())) {

            user.setVerifyResetCodePassword(true);

            userRepository.save(user);

        } else {
            throw new BadRequestException("Invalid or expired verification code");
        }
    }

    @Override
    public void resetPassword(String id, String pwd) {
        final User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found with id: " + id));
        PersonResponse person = personService.getPersonById(user.getPersonId());

        if (user.getVerifyResetCodePassword()) {

            user.setVerifyResetCodePassword(false);
            user.setPassword(passwordEncoder.encode(pwd));

            final String resetCodePassword = codeGenerator.generateVerificationCode();
            user.setResetCodePassword(resetCodePassword);


            User userSave = userRepository.save(user);

            sendEmail.sendChangePasswordSuccessfulByEmail(userSave.getEmail(), person.getFullName());

        } else {
            throw new BadRequestException("Invalid reset password");
        }
    }

    @Override
    public String createUserReviewer(UserRequest userRequest) {
        validateEmail(userRequest.getEmail());
        validateEmailStructure(userRequest.getEmail());

        PersonResponse person = personService.getPersonById(userRequest.getPersonId());

        User user = userMapper.toUser(userRequest);

        // Set status PREACTIVE
        user.setStatus(UserStatus.ACTIVE);

        // Set user type APPLICANT
        user.setType(UserType.REVIEWER);

        // Send code email
        user.setVerifyEmail(true);
        user.setVerificationCodeEmail(null);
        user.setVerificationCodeEmailSentAt(null);

        // Send code phone whatsapp
        user.setVerifyPhone(true);
        user.setVerificationCodePhone(null);
        user.setVerificationCodePhoneSentAt(null);

        user.setCreatedAt(LocalDate.now());
        user.setUpdatedAt(LocalDate.now());
        user.setLastAccess(null);

        final String passwordRandom = passwordGenerator.generateRandomPassword();
        user.setPassword(passwordEncoder.encode(passwordRandom));

        User userSave = userRepository.save(user);

        sendEmail.sendPasswordByEmail(user.getEmail(), passwordRandom, person.getFullName());

        return userSave.getId();
    }

    @Override
    public List<ReviewerResponse> getReviewerActive() {
        List<User> userList = userRepository.findByStatusAndType(
                UserStatus.ACTIVE.name(), UserType.REVIEWER.name()
        );

        return userList.stream()
                .map(user -> {
                    PersonResponse person = personService.getPersonById(user.getPersonId());

                    ReviewerResponse response = userMapper.toReviewerResponse(user);
                    response.setFullName(person.getFullName());
                    response.setPerson(person);

                    return response;
                })
                .collect(Collectors.toList());
    }

}
