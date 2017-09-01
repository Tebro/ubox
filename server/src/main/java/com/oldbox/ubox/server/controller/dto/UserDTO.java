package com.oldbox.ubox.server.controller.dto;

import com.oldbox.ubox.server.entity.User;

public class UserDTO {

    public String username;


    public UserDTO(User user){
        this.username = user.getUsername();
    }

}
