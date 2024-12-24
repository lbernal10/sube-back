package com.sube.plus.apaseo.sube_back.model.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddressRequest {

    private String zip; // 37235 - API

    private String street; // Fuji

    private String exterior; // 206

    private String interior; // null

    private String neighborhood; // Jardines del sol

    private String city; // Leon - API

    private String state; // GT - API

    private String locality; // Leon de los Aldamas - API ????

}
