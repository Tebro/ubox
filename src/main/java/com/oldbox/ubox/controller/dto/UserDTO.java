package com.oldbox.ubox.controller.dto;

import com.oldbox.ubox.entity.ProfileFieldValue;
import com.oldbox.ubox.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {

    public String username;
    public List<ProfileFieldDTO> profileFields = new ArrayList<>();


    public UserDTO(User user){
        this.username = user.getUsername();

        if(user.getProfileFieldValues() != null && user.getProfileFieldValues().size() > 0){
            for(ProfileFieldValue fieldValue: user.getProfileFieldValues()){
                this.profileFields.add(new ProfileFieldDTO(fieldValue));
            }
        }
    }

}
