package com.oldbox.ubox.user;

import com.oldbox.ubox.profile.ProfileFieldDTO;
import com.oldbox.ubox.profile.ProfileFieldValue;

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
