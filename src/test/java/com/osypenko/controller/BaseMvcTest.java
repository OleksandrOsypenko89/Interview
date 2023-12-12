package com.osypenko.controller;

import com.osypenko.model.users.User;
import com.osypenko.services.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@SpringBootTest
@AutoConfigureMockMvc
public class BaseMvcTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserService userService;

    public final String USER_MAIL = "demo@gmail.com";
    public User user;

    @BeforeEach
    void setup() {
        user = userService.findByEmail(USER_MAIL).orElseThrow();
    }

    public ResultActions perform(MockHttpServletRequestBuilder builder) throws Exception {
        return mockMvc.perform(builder);
    }
}
