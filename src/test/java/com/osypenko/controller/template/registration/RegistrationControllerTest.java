package com.osypenko.controller.template.registration;

import com.osypenko.controller.BaseMvcTests;
import org.junit.jupiter.api.Test;

import static com.osypenko.TestConstants.*;
import static com.osypenko.constant.Constant.OLEKSANDR_GMAIL_COM;
import static com.osypenko.constant.Endpoints.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class RegistrationControllerTest extends BaseMvcTests {

    @Test
    void registration() throws Exception {
        perform(get(REGISTRATION))
                .andExpect(status().isOk())
                .andExpect(view().name(DIRECTORY_REGISTRATION + REGISTRATION));
    }

    @Test
    void registrationNewUserCorrect() throws Exception {
        perform(post(GET_REGISTRATION_CODE)
                .param("firstName", TEST_FIRST_NAME)
                .param("lastName", TEST_LAST_NAME)
                .param("email", OLEKSANDR_GMAIL_COM)
                .param("password", TEST_PASSWORD)
        )
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(CODE_FOR_REGISTRATION))
                .andExpect(view().name(REDIRECT + CODE_FOR_REGISTRATION));
    }

    @Test
    void registrationNewUserWrong() throws Exception {
        perform(post(GET_REGISTRATION_CODE)
                .param("firstName", TEST_FIRST_NAME)
                .param("lastName", TEST_LAST_NAME)
                .param("email", EXPECTED_USER_EMAIL)
                .param("password", TEST_PASSWORD)
        )
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(REGISTRATION))
                .andExpect(view().name(REDIRECT + REGISTRATION));
    }
}