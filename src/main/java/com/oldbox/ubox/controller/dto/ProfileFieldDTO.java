package com.oldbox.ubox.controller.dto;

import com.oldbox.ubox.entity.ProfileFieldValue;

public class ProfileFieldDTO {
    public String name;
    public String value;

    public ProfileFieldDTO(ProfileFieldValue profileFieldValue) {
        this.name = profileFieldValue.getProfileField().getName();
        this.value = profileFieldValue.getValue();
    }

}
