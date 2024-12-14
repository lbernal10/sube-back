package com.sube.plus.apaseo.sube_back.model.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginRequest {

    private String email;

    private String password;

}
