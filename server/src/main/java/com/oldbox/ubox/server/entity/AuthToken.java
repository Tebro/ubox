package com.oldbox.ubox.server.entity;


import javax.persistence.*;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.logging.Logger;

@Entity
@Table(name = "auth_tokens")
public class AuthToken {

    static Logger logger = Logger.getLogger(AuthToken.class.getName());

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, unique = true)
    private String token;

    public AuthToken(){}

    public AuthToken(String token) {
        this.token = token;
    }

    public static AuthToken newForUser(User user) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        AuthToken token = new AuthToken();

        logger.info("User is : " + user);


        String from = "TOKEN" + user.getId() + System.currentTimeMillis();
        //byte[] fromB = from.getBytes("UTF-8");

        MessageDigest md = MessageDigest.getInstance("MD5");
        token.token = (new HexBinaryAdapter()).marshal(md.digest(from.getBytes()));

//        token.token = new String(md.digest(fromB));
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
