package com.osypenko.controller.template;

import com.osypenko.controller.BaseMvcTest;
import org.junit.jupiter.api.Test;

import static com.osypenko.constant.Endpoints.DIRECTORY_USER_PAGES;
import static com.osypenko.constant.Endpoints.LOGIN;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class LoginControllerTest extends BaseMvcTest {

    @Test
    void login() throws Exception {
        perform(get(LOGIN))
                .andExpect(status().isOk())
                .andExpect(view().name(DIRECTORY_USER_PAGES + LOGIN));
    }
}