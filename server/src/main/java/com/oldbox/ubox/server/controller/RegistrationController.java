package com.oldbox.ubox.server.controller;

import com.oldbox.ubox.server.entity.User;
import com.oldbox.ubox.server.repository.UserRepository;
import com.oldbox.ubox.server.controller.dto.RegistrationRequestDTO;
import com.oldbox.ubox.server.controller.dto.RegistrationResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegistrationController {

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

        if (userRepository.findByUsername(user.getUsername()) == null){
            return new RegistrationResponseDTO(ResponseCode.BAD_REQUEST, "Username in use");
        }

        User savedUser = userRepository.save(user);

        return new RegistrationResponseDTO(ResponseCode.OK);

    }

}
