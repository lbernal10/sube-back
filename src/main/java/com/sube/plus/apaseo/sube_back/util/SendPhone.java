package com.sube.plus.apaseo.sube_back.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class SendPhone {

    @Value("${app.twilio.account.sid}")
    private String appAccountSID;

    @Value("${app.twilio.account.token}")
    private String appAccountToken;

    @Value("${app.twilio.account.phone}")
    private String appAccountPhone;

    public void sendVerificationPhone(String phone, String verificationCode) {
        Twilio.init(appAccountSID, appAccountToken);

        Message message = Message
                .creator(new com.twilio.type.PhoneNumber(phone),
                        new com.twilio.type.PhoneNumber(appAccountPhone),
                        "(Sube+) Su código de verificación es: " + verificationCode)
                .create();


        System.out.println(message.getBody());
        log.info("Send successfully code for your phone");
    }

}
