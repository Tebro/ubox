package com.oldbox.ubox.server.controller;

import com.oldbox.ubox.server.controller.dto.LoginRequestDTO;
import com.oldbox.ubox.server.controller.dto.LoginResponseDTO;
import com.oldbox.ubox.server.entity.AuthToken;
import com.oldbox.ubox.server.entity.User;
import com.oldbox.ubox.server.repository.TokenRepository;
import com.oldbox.ubox.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
public class AuthController {

    private UserRepository userRepository;

    private TokenRepository tokenRepository;

    @Autowired
    public AuthController(UserRepository userRepository, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }

    @RequestMapping("/api/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO request, HttpServletResponse response, @CookieValue("token") String cookieToken) {

        if(tokenRepository.findAuthTokenByToken(cookieToken).getUser().getUsername().equalsIgnoreCase(request.username)){
            response.addCookie(new Cookie("token", cookieToken));
            return LoginResponseDTO.withToken(cookieToken);
        }

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

        response.addCookie(new Cookie("token", savedToken.getToken()));

        return LoginResponseDTO.withToken(savedToken.getToken());
    }

}
