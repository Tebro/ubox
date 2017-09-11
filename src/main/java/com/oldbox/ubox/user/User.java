package com.oldbox.ubox.user;

import com.oldbox.ubox.auth.AuthToken;
import com.oldbox.ubox.profile.ProfileFieldValue;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<AuthToken> tokens;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<ProfileFieldValue> profileFieldValues;

    public User(){}

    public User(String username) {
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<AuthToken> getTokens() {
        return tokens;
    }

    public void addToken(AuthToken token) {
        this.tokens.add(token);
    }

    public void deleteToken(AuthToken token) {
        this.tokens.remove(token);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<ProfileFieldValue> getProfileFieldValues() {
        return profileFieldValues;
    }

    public void setTokens(Set<AuthToken> tokens) {
        this.tokens = tokens;
    }

    public void setProfileFieldValues(Set<ProfileFieldValue> profileFieldValues) {
        this.profileFieldValues = profileFieldValues;
    }
}
