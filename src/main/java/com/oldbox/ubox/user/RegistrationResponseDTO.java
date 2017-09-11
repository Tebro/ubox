package com.oldbox.ubox.user;

import com.oldbox.ubox.ResponseCode;

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
