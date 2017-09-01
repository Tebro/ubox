package com.oldbox.ubox.server.controller;

import com.oldbox.ubox.server.controller.dto.LoginRequestDTO;
import com.oldbox.ubox.server.controller.dto.LoginResponseDTO;
import com.oldbox.ubox.server.entity.AuthToken;
import com.oldbox.ubox.server.entity.User;
import com.oldbox.ubox.server.repository.TokenRepository;
import com.oldbox.ubox.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
public class AuthController {

    private Logger logger = Logger.getLogger(AuthController.class.getName());

    private UserRepository userRepository;

    private TokenRepository tokenRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(UserRepository userRepository, TokenRepository tokenRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping(value = "/api/login", method = RequestMethod.POST)
    public LoginResponseDTO login(@RequestBody LoginRequestDTO request) {

        User user = userRepository.findByUsername(request.username);

        if(user == null || !passwordEncoder.matches(request.password, user.getPassword())){
            return new LoginResponseDTO(ResponseCode.NOT_FOUND, "Invalid credentials");
        }

        //TODO: Passwords
        AuthToken token;

        try {
            token = AuthToken.newForUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return new LoginResponseDTO(ResponseCode.SERVER_ERROR);
        }

        logger.info("User: " + user.getUsername() + " logged in.");

        token.setUser(user);
        tokenRepository.save(token);
        return LoginResponseDTO.withToken(token.getToken());
    }

    @RequestMapping("/api/renew")
    public LoginResponseDTO renew(@RequestParam String token){
        AuthToken old = tokenRepository.findAuthTokenByToken(token);
        User user = userRepository.findByTokensContains(old);
        logger.info("Renewing token for" + user.getUsername());
        try {
            AuthToken newToken = AuthToken.newForUser(user);
            newToken.setUser(user);
            tokenRepository.save(newToken);
            tokenRepository.delete(old);
            return LoginResponseDTO.withToken(newToken.getToken());
        } catch (Exception e) {
            e.printStackTrace();
            return new LoginResponseDTO(ResponseCode.SERVER_ERROR);
        }
    }

}
