package com.oldbox.ubox.server.entity;


import javax.persistence.*;
import java.util.Set;

@Entity
public class ProfileField {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(mappedBy = "profileField", cascade = CascadeType.ALL)
    private Set<ProfileFieldValue> profileFieldValues;

    @Column(nullable = false, unique = true)
    private String name;


    public ProfileField(){}

    public ProfileField(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<ProfileFieldValue> getProfileFieldValues() {
        return profileFieldValues;
    }

    public void setProfileFieldValues(Set<ProfileFieldValue> profileFieldValues) {
        this.profileFieldValues = profileFieldValues;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
