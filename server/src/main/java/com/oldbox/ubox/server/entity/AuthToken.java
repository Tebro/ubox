package com.oldbox.ubox.server.entity;


import javax.persistence.*;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Entity
@Table(name = "auth_tokens")
public class AuthToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @JoinColumn(foreignKey = @ForeignKey(name = "FK_USER"))
    private User user;

    @Column(nullable = false, unique = true)
    private String token;

    public static AuthToken newForUser(User user) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        AuthToken token = new AuthToken();

        String from = "TOKEN" + user.getId() + System.currentTimeMillis();
        byte[] fromB = from.getBytes("UTF-8");

        MessageDigest md = MessageDigest.getInstance("MD5");
        token.token = md.digest(fromB).toString();
        return token;
    }

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
