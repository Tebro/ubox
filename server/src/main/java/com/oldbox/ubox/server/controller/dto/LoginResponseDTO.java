package com.oldbox.ubox.server.controller.dto;

import com.oldbox.ubox.server.controller.ResponseCode;

public class LoginResponseDTO {
    public ResponseCode code;
    public String token;

    public LoginResponseDTO(ResponseCode code) {
        this.code = code;
    }

    public static LoginResponseDTO withToken(String token){
        LoginResponseDTO r = new LoginResponseDTO(ResponseCode.OK);
        r.token = token;
        return r;
    }
}
