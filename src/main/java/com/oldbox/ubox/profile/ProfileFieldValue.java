package com.oldbox.ubox.profile;


import com.oldbox.ubox.user.User;

import javax.persistence.*;

@Entity
public class ProfileFieldValue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "profile_field_id")
    private ProfileField profileField;

    @Column(nullable = false)
    private String value;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ProfileField getProfileField() {
        return profileField;
    }

    public void setProfileField(ProfileField profileField) {
        this.profileField = profileField;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
