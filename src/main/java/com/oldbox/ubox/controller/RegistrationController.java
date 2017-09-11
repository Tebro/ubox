package com.oldbox.ubox.controller;

import com.oldbox.ubox.controller.dto.RegistrationRequestDTO;
import com.oldbox.ubox.controller.dto.RegistrationResponseDTO;
import com.oldbox.ubox.repository.UserRepository;
import com.oldbox.ubox.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class RegistrationController {

    private Logger logger = Logger.getLogger(RegistrationController.class.getName());
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping(path = "/api/register", method = RequestMethod.POST)
    public RegistrationResponseDTO registerApi(@RequestBody RegistrationRequestDTO request){
        if(request.getUsername() == null || request.getUsername().length() < 1){
            return new RegistrationResponseDTO(ResponseCode.BAD_REQUEST, "Invalid username provided");
        }

        User user = new User(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        if (userRepository.findByUsername(user.getUsername()) != null){
            return new RegistrationResponseDTO(ResponseCode.BAD_REQUEST, "Username in use");
        }

        User savedUser = userRepository.save(user);

        logger.info("Registered new user: " + savedUser.getUsername());

        return new RegistrationResponseDTO(ResponseCode.OK);

    }

}
