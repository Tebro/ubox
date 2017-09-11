package com.oldbox.ubox.controller;

import com.google.gson.Gson;
import com.oldbox.ubox.user.RegistrationRequestDTO;
import com.oldbox.ubox.user.RegistrationController;
import com.oldbox.ubox.user.UserRepository;
import com.oldbox.ubox.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RegistrationController.class)
public class RegistrationControllerTest {

    private final String username = "username";
    private final String password = "password";
    private final String passwordHash = "passwordHash";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    private RegistrationRequestDTO request(){
        RegistrationRequestDTO request = new RegistrationRequestDTO();
        request.setUsername(username);
        request.setPassword(password);
        return request;
    }

    private User getUserWithId(){
        User user = new User(username);
        user.setPassword(passwordHash);
        user.setId(1);
        return user;
    }


    @Test
    public void registerApi() throws Exception {
        given(passwordEncoder.encode(password)).willReturn(passwordHash);
        when(userRepository.save(argThat(new ArgumentMatcher<User>() {
            @Override
            public boolean matches(Object argument) {
                return ((User) argument).getUsername().equals(username);
            }
        }))).thenReturn(getUserWithId());
        given(userRepository.findByUsername(username)).willReturn(null);

        String json = new Gson().toJson(request());

        mvc.perform(post("/api/register").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(json))
                .andExpect(status().isOk());
    }

}