package com.oldbox.ubox.profile;

import com.oldbox.ubox.profile.ProfileFieldValue;

public class ProfileFieldDTO {
    public String name;
    public String value;

    public ProfileFieldDTO(ProfileFieldValue profileFieldValue) {
        this.name = profileFieldValue.getProfileField().getName();
        this.value = profileFieldValue.getValue();
    }

}
