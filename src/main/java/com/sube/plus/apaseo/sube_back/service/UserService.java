package com.sube.plus.apaseo.sube_back.service;

import com.sube.plus.apaseo.sube_back.model.request.UserRequest;

public interface UserService {

    String createUserApplicant(UserRequest userRequest);

    void verifyUserEmail(String id, String verificationCodeEmail);

    void verifyUserPhone(String id, String verificationCodePhone);

    void sendVerificationCodeEmail(String id, String email);

    void sendVerificationCodePhone(String id, String phone);

    void setAndSendRandomPassword(String id, String email);
}
