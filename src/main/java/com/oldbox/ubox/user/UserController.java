package com.oldbox.ubox.user;


import com.oldbox.ubox.auth.TokenRepository;
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
