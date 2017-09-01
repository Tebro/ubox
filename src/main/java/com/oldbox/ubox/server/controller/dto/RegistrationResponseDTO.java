package com.oldbox.ubox.server.controller.dto;

import com.oldbox.ubox.server.controller.ResponseCode;

public class RegistrationResponseDTO {

    public ResponseCode code;

    public String message;

    public RegistrationResponseDTO(ResponseCode code) {
        this.code = code;
    }

    public RegistrationResponseDTO(ResponseCode code, String message) {
        this.code = code;
        this.message = message;
    }
}
