package com.oldbox.ubox.auth;

import com.oldbox.ubox.ResponseCode;

public class LoginResponseDTO {
    public ResponseCode code;
    public String token;

    public LoginResponseDTO(ResponseCode code) {
        this.code = code;
    }

    public LoginResponseDTO(ResponseCode code, String token) {
        this.code = code;
        this.token = token;
    }

    public static LoginResponseDTO withToken(String token){
        LoginResponseDTO r = new LoginResponseDTO(ResponseCode.OK);
        r.token = token;
        return r;
    }
}
