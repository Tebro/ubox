package com.oldbox.ubox.controller;

import com.google.gson.Gson;
import com.oldbox.ubox.auth.AuthController;
import com.oldbox.ubox.auth.TokenRepository;
import com.oldbox.ubox.user.UserRepository;
import com.oldbox.ubox.auth.LoginRequestDTO;
import com.oldbox.ubox.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AuthController.class)
public class AuthControllerTest {

    private final int userId = 1;
    private final String username = "username";
    private final String password = "password";
    private final String passwordHash = "passwordHash";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private TokenRepository tokenRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    private User getUser(){
        User u = new User(username);
        u.setId(userId);
        u.setPassword(passwordHash);
        return u;
    }

    private LoginRequestDTO requestDTO(){
        LoginRequestDTO request = new LoginRequestDTO();
        request.username = username;
        request.password = password;
        return request;
    }

    private void setupMocks(){
        when(userRepository.findByUsername(username)).thenReturn(getUser());
        when(passwordEncoder.matches(password, passwordHash)).thenReturn(true);
    }

    @Test
    public void loginWithValidCredentials() throws Exception {
        setupMocks();

        String json = (new Gson()).toJson(requestDTO());

        mvc.perform(post("/api/login").content(json).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("OK"))
                .andExpect(jsonPath("$.token").isString())
                .andExpect(jsonPath("$.token").isNotEmpty());
    }

    @Test
    public void loginWithInvalidCredentials() throws Exception {
        setupMocks();

        LoginRequestDTO request = requestDTO();
        request.password = "foobar";

        String json = (new Gson()).toJson(request);

        mvc.perform(post("/api/login").content(json).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk()) // TODO: The controller should also use HTTP status codes
                .andExpect(jsonPath("$.code").value("NOT_FOUND"));

    }


}