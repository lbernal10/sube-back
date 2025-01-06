package com.sube.plus.apaseo.sube_back.service;

import com.sube.plus.apaseo.sube_back.model.request.PersonRequest;
import com.sube.plus.apaseo.sube_back.model.request.UserRequest;
import com.sube.plus.apaseo.sube_back.model.response.PersonResponse;
import com.sube.plus.apaseo.sube_back.model.response.UserResponse;

public interface UserService {

    String createUserApplicant(UserRequest userRequest);

    void verifyUserEmail(String id, String verificationCodeEmail);

    void verifyUserPhone(String id, String verificationCodePhone);

    void sendVerificationCodeEmail(String id, String email);

    void sendVerificationCodePhone(String id, String phone);

    void setAndSendRandomPassword(String id, String email);

    UserResponse getUserById(String id);

    UserResponse updateUser(String id, UserRequest userRequest);

    void deleteApplicant(String id);

    void deleteReviewer(String id);

    void sendCodeResetPassword(String email);

    void validateCodeResetPassword(String id, String code);

    void resetPassword(String id, String pwd);

    String createUserReviewer(UserRequest userRequest);

}
