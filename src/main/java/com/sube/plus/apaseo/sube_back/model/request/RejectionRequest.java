package com.sube.plus.apaseo.sube_back.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RejectionRequest {
    private String reason; // Motivo del rechazo
}