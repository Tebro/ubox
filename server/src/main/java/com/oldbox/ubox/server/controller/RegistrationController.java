package com.oldbox.ubox.server.controller;

import com.oldbox.ubox.server.controller.dto.RegistrationRequestDTO;
import com.oldbox.ubox.server.controller.dto.RegistrationResponseDTO;
import com.oldbox.ubox.server.entity.User;
import com.oldbox.ubox.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class RegistrationController {

    private Logger logger = Logger.getLogger(RegistrationController.class.getName());
    private final UserRepository userRepository;

    @Autowired
    public RegistrationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(path = "/api/register", method = RequestMethod.POST)
    public RegistrationResponseDTO registerApi(@RequestBody RegistrationRequestDTO request){
        if(request.getUsername() == null || request.getUsername().length() < 1){
            return new RegistrationResponseDTO(ResponseCode.BAD_REQUEST, "Invalid username provided");
        }

        User user = new User(request.getUsername());

        if (userRepository.findByUsername(user.getUsername()) != null){
            return new RegistrationResponseDTO(ResponseCode.BAD_REQUEST, "Username in use");
        }

        User savedUser = userRepository.save(user);

        logger.info("Registered new user: " + savedUser.getUsername());

        return new RegistrationResponseDTO(ResponseCode.OK);

    }

}
