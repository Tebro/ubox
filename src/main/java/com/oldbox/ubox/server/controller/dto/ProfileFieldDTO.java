package com.oldbox.ubox.server.controller.dto;

import com.oldbox.ubox.server.entity.ProfileFieldValue;

public class ProfileFieldDTO {
    public String name;
    public String value;

    public ProfileFieldDTO(ProfileFieldValue profileFieldValue) {
        this.name = profileFieldValue.getProfileField().getName();
        this.value = profileFieldValue.getValue();
    }

}
