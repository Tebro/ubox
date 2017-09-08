package com.oldbox.ubox.server.controller;

import com.oldbox.ubox.server.entity.AuthToken;
import com.oldbox.ubox.server.entity.ProfileField;
import com.oldbox.ubox.server.entity.ProfileFieldValue;
import com.oldbox.ubox.server.entity.User;
import com.oldbox.ubox.server.repository.TokenRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TokenRepository tokenRepository;

    private final String token = "testingtoken";
    private final String username = "test";

    private AuthToken authToken(){
        AuthToken authToken = new AuthToken();
        authToken.setToken(token);

        User user = new User();
        user.setId(1);
        user.setUsername(username);

        Set<ProfileFieldValue> profileFieldValues = new HashSet<>();

        ProfileFieldValue profileFieldValue = new ProfileFieldValue();
        profileFieldValue.setId(1);
        profileFieldValue.setProfileField(new ProfileField("email"));
        profileFieldValue.setUser(user);
        profileFieldValue.setValue("test@test.test");

        profileFieldValues.add(profileFieldValue);

        user.setProfileFieldValues(profileFieldValues);

        authToken.setUser(user);

        return authToken;
    }

    @Test
    public void getUser() throws Exception {

        given(tokenRepository.findAuthTokenByToken(token)).willReturn(authToken());

        mvc.perform(get("/api/user?token=" + token)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is(username)))
                .andExpect(jsonPath("$.profileFields[0].name", is("email")))
                .andExpect(jsonPath("$.profileFields[0].value", is("test@test.test")));


    }

}