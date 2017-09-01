package com.oldbox.ubox.server.controller;


import com.oldbox.ubox.server.controller.dto.UserDTO;
import com.oldbox.ubox.server.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final TokenRepository tokenRepository;

    @Autowired
    public UserController(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @RequestMapping("/api/user")
    public UserDTO getUser(@RequestParam String token){
        return new UserDTO(tokenRepository.findAuthTokenByToken(token).getUser());

    }

}