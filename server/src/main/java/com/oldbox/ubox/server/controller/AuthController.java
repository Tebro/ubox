package com.oldbox.ubox.server.controller;

import com.oldbox.ubox.server.controller.dto.LoginRequestDTO;
import com.oldbox.ubox.server.controller.dto.LoginResponseDTO;
import com.oldbox.ubox.server.entity.AuthToken;
import com.oldbox.ubox.server.entity.User;
import com.oldbox.ubox.server.repository.TokenRepository;
import com.oldbox.ubox.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    private UserRepository userRepository;

    private TokenRepository tokenRepository;

    @Autowired
    public AuthController(UserRepository userRepository, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }

    @RequestMapping(value = "/api/login", method = RequestMethod.POST)
    public LoginResponseDTO login(@RequestBody LoginRequestDTO request) {

        User user = userRepository.findByUsername(request.username);

        //TODO: Passwords
        AuthToken token;

        try {
            token = AuthToken.newForUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return new LoginResponseDTO(ResponseCode.SERVER_ERROR);
        }

        AuthToken savedToken = tokenRepository.save(token);

        return LoginResponseDTO.withToken(savedToken.getToken());
    }

    @RequestMapping("/api/renew")
    public LoginResponseDTO renew(@RequestParam String token){
        AuthToken old = tokenRepository.findAuthTokenByToken(token);

        try {
            AuthToken newToken = tokenRepository.save(AuthToken.newForUser(old.getUser()));
            tokenRepository.delete(old);
            return LoginResponseDTO.withToken(newToken.getToken());
        } catch (Exception e) {
            e.printStackTrace();
            return new LoginResponseDTO(ResponseCode.SERVER_ERROR);
        }
    }

}
